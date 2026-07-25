package com.kormosathi.app.viewmodel

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.kormosathi.app.auth.AuthRepository
import com.kormosathi.app.model.User
import com.kormosathi.app.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AuthViewModel(
    private val authRepository: AuthRepository = AuthRepository(),
    private val userRepository: UserRepository = UserRepository()
) : ViewModel() {

    data class AuthUiState(
        val isLoading: Boolean = false,
        val errorMessage: String = "",
        val phoneNumber: String = "",
        val isOtpSent: Boolean = false,
        val isAuthenticated: Boolean = false
    )

    private val _uiState = MutableStateFlow(AuthUiState(isAuthenticated = authRepository.isLoggedIn()))
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun sendOtp(activity: Activity, phoneNumber: String) {
        val normalizedPhone = phoneNumber.trim()
        if (normalizedPhone.length != 10) {
            _uiState.update { it.copy(errorMessage = "অনুগ্রহ করে ১০ সংখ্যার মোবাইল নম্বর লিখুন") }
            return
        }

        _uiState.update { it.copy(isLoading = true, errorMessage = "", phoneNumber = normalizedPhone) }

        authRepository.sendOtp(
            activity = activity,
            phoneNumber = normalizedPhone,
            onCodeSent = {
                _uiState.update { it.copy(isLoading = false, isOtpSent = true) }
            },
            onVerificationCompleted = { credential ->
                authRepository.signInWithCredential(
                    credential = credential,
                    onSuccess = { handleSuccessfulSignIn(normalizedPhone) },
                    onError = { message ->
                        _uiState.update { it.copy(isLoading = false, errorMessage = message) }
                    }
                )
            },
            onError = { message ->
                _uiState.update { it.copy(isLoading = false, errorMessage = message) }
            }
        )
    }

    fun verifyOtp(otp: String) {
        if (otp.length != 6) {
            _uiState.update { it.copy(errorMessage = "অনুগ্রহ করে ৬-সংখ্যার OTP লিখুন") }
            return
        }

        _uiState.update { it.copy(isLoading = true, errorMessage = "") }

        authRepository.verifyOtp(
            otp = otp,
            onSuccess = { handleSuccessfulSignIn(_uiState.value.phoneNumber) },
            onError = { message ->
                _uiState.update { it.copy(isLoading = false, errorMessage = message) }
            }
        )
    }

    fun isUserLoggedIn(): Boolean = authRepository.isLoggedIn()

    fun logout() {
        authRepository.signOut()
        _uiState.update { AuthUiState() }
    }

    fun clearError() {
        _uiState.update { it.copy(errorMessage = "") }
    }

    private fun handleSuccessfulSignIn(phoneNumber: String) {
        val currentUser = authRepository.currentUser() ?: run {
            _uiState.update { it.copy(isLoading = false, errorMessage = "Authentication failed") }
            return
        }

        val user = User(
            uid = currentUser.uid,
            phone = phoneNumber,
            role = "customer",
            profileCompleted = false
        )

        userRepository.saveUser(
            user = user,
            onSuccess = {
                _uiState.update { it.copy(isLoading = false, isAuthenticated = true, errorMessage = "") }
            },
            onFailure = { exception ->
                _uiState.update {
                    it.copy(isLoading = false, errorMessage = exception.message ?: "Unable to save user")
                }
            }
        )
    }
}
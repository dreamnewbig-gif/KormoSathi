package com.kormosathi.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kormosathi.app.model.UserProfile
import com.kormosathi.app.repository.ProfileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class ProfileUiState(

    val isLoading: Boolean = false,

    val profile: UserProfile? = null,

    val isSuccess: Boolean = false,

    val errorMessage: String = ""

)

class ProfileViewModel(

    private val repository: ProfileRepository =
        ProfileRepository()

) : ViewModel() {

    private val _uiState =
        MutableStateFlow(
            ProfileUiState()
        )

    val uiState: StateFlow<ProfileUiState> =
        _uiState.asStateFlow()

    fun loadProfile() {

        viewModelScope.launch {

            _uiState.value =
                _uiState.value.copy(
                    isLoading = true,
                    errorMessage = ""
                )

            try {

                val profile =
                    repository.getProfile()

                _uiState.value =
                    _uiState.value.copy(
                        isLoading = false,
                        profile = profile,
                        errorMessage =
                            if (
                                profile == null
                            ) {
                                "Profile not found"
                            } else {
                                ""
                            }
                    )

            } catch (
                e: Exception
            ) {

                _uiState.value =
                    _uiState.value.copy(
                        isLoading = false,
                        errorMessage =
                            e.message
                                ?: "Failed to load profile"
                    )

            }

        }

    }

    fun saveProfile(
        profile: UserProfile
    ) {

        viewModelScope.launch {

            _uiState.value =
                _uiState.value.copy(
                    isLoading = true,
                    isSuccess = false,
                    errorMessage = ""
                )

            repository
                .saveProfile(profile)
                .onSuccess {

                    _uiState.value =
                        _uiState.value.copy(
                            isLoading = false,
                            profile = profile,
                            isSuccess = true,
                            errorMessage = ""
                        )

                }
                .onFailure {
                        exception ->

                    _uiState.value =
                        _uiState.value.copy(
                            isLoading = false,
                            isSuccess = false,
                            errorMessage =
                                exception.message
                                    ?: "Failed to save profile"
                        )

                }

        }

    }

    fun clearSuccess() {

        _uiState.value =
            _uiState.value.copy(
                isSuccess = false
            )

    }

    fun clearError() {

        _uiState.value =
            _uiState.value.copy(
                errorMessage = ""
            )

    }

}
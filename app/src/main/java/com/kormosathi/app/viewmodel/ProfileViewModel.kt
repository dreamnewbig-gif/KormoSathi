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
    val isSuccess: Boolean = false,
    val errorMessage: String = ""
)

class ProfileViewModel : ViewModel() {

    private val repository = ProfileRepository()

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    fun saveProfile(profile: UserProfile) {

        viewModelScope.launch {

            _uiState.value = ProfileUiState(isLoading = true)

            repository.saveProfile(profile)
                .onSuccess {

                    _uiState.value = ProfileUiState(
                        isLoading = false,
                        isSuccess = true
                    )

                }
                .onFailure {

                    _uiState.value = ProfileUiState(
                        isLoading = false,
                        errorMessage = it.message ?: "Unknown error"
                    )

                }

        }

    }

    fun loadProfile(onResult: (UserProfile?) -> Unit) {

        viewModelScope.launch {

            onResult(repository.getProfile())

        }

    }

}
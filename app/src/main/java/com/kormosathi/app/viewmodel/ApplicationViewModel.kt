package com.kormosathi.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kormosathi.app.model.JobApplication
import com.kormosathi.app.repository.ApplicationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class ApplicationUiState(
    val isLoading: Boolean = false,
    val applications: List<JobApplication> = emptyList(),
    val isSuccess: Boolean = false,
    val errorMessage: String = "",
    val hasApplied: Boolean = false
)

class ApplicationViewModel : ViewModel() {

    private val repository = ApplicationRepository()

    private val _uiState = MutableStateFlow(ApplicationUiState())
    val uiState: StateFlow<ApplicationUiState> = _uiState.asStateFlow()

    fun applyForJob(
        jobId: String,
        applicantName: String,
        phone: String
    ) {
        viewModelScope.launch {
            _uiState.value = ApplicationUiState(isLoading = true)

            repository.applyForJob(jobId, applicantName, phone)
                .onSuccess {
                    _uiState.value = ApplicationUiState(
                        isLoading = false,
                        isSuccess = true
                    )
                }
                .onFailure { exception ->
                    _uiState.value = ApplicationUiState(
                        isLoading = false,
                        errorMessage = exception.message ?: "Failed to apply for job"
                    )
                }
        }
    }

    fun loadMyApplications() {
        viewModelScope.launch {
            _uiState.value = ApplicationUiState(isLoading = true)

            val applications = repository.getMyApplications()
            _uiState.value = ApplicationUiState(
                isLoading = false,
                applications = applications
            )
        }
    }

    fun checkIfApplied(jobId: String) {
        viewModelScope.launch {
            val hasApplied = repository.hasUserApplied(jobId)
            _uiState.value = _uiState.value.copy(hasApplied = hasApplied)
        }
    }

    fun clearSuccess() {
        _uiState.value = _uiState.value.copy(isSuccess = false)
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(errorMessage = "")
    }
}

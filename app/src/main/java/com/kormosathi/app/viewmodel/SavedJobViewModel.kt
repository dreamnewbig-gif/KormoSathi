package com.kormosathi.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kormosathi.app.model.SavedJob
import com.kormosathi.app.repository.SavedJobRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class SavedJobUiState(
    val isLoading: Boolean = false,
    val savedJobs: List<SavedJob> = emptyList(),
    val isSuccess: Boolean = false,
    val errorMessage: String = ""
)

class SavedJobViewModel : ViewModel() {

    private val repository = SavedJobRepository()

    private val _uiState = MutableStateFlow(SavedJobUiState())
    val uiState: StateFlow<SavedJobUiState> = _uiState.asStateFlow()

    fun loadSavedJobs() {
        viewModelScope.launch {
            _uiState.value = SavedJobUiState(isLoading = true)

            try {
                val savedJobs = repository.getSavedJobs()
                _uiState.value = SavedJobUiState(
                    isLoading = false,
                    savedJobs = savedJobs
                )
            } catch (exception: Exception) {
                _uiState.value = SavedJobUiState(
                    isLoading = false,
                    errorMessage = exception.message ?: "Failed to load saved jobs"
                )
            }
        }
    }

    fun addBookmark(jobId: String, jobTitle: String, companyName: String, district: String, category: String, salary: Double) {
        viewModelScope.launch {
            repository.saveJob(jobId)
                .onSuccess {
                    _uiState.value = _uiState.value.copy(isSuccess = true)
                    loadSavedJobs()
                }
                .onFailure { exception ->
                    _uiState.value = _uiState.value.copy(
                        errorMessage = exception.message ?: "Failed to add bookmark"
                    )
                }
        }
    }

    fun removeBookmark(jobId: String) {
        viewModelScope.launch {
            repository.unsaveJob(jobId)
                .onSuccess {
                    _uiState.value = _uiState.value.copy(isSuccess = true)
                    loadSavedJobs()
                }
                .onFailure { exception ->
                    _uiState.value = _uiState.value.copy(
                        errorMessage = exception.message ?: "Failed to remove bookmark"
                    )
                }
        }
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(errorMessage = "")
    }
}

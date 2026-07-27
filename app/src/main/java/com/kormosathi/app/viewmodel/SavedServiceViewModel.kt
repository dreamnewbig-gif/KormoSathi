package com.kormosathi.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kormosathi.app.model.SavedService
import com.kormosathi.app.repository.SavedServiceRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class SavedServiceUiState(
    val isLoading: Boolean = false,
    val savedServices: List<SavedService> = emptyList(),
    val isSuccess: Boolean = false,
    val errorMessage: String = ""
)

class SavedServiceViewModel : ViewModel() {

    private val repository = SavedServiceRepository()

    private val _uiState = MutableStateFlow(SavedServiceUiState())
    val uiState: StateFlow<SavedServiceUiState> = _uiState.asStateFlow()

    fun loadSavedServices() {
        viewModelScope.launch {
            _uiState.value = SavedServiceUiState(isLoading = true)

            try {
                val savedServices = repository.getSavedServices()
                _uiState.value = SavedServiceUiState(
                    isLoading = false,
                    savedServices = savedServices
                )
            } catch (exception: Exception) {
                _uiState.value = SavedServiceUiState(
                    isLoading = false,
                    errorMessage = exception.message ?: "Failed to load saved Services"
                )
            }
        }
    }

    fun addBookmark(ServiceId: String, ServiceTitle: String, companyName: String, district: String, category: String, salary: Double) {
        viewModelScope.launch {
            repository.saveService(ServiceId)
                .onSuccess {
                    _uiState.value = _uiState.value.copy(isSuccess = true)
                    loadSavedServices()
                }
                .onFailure { exception ->
                    _uiState.value = _uiState.value.copy(
                        errorMessage = exception.message ?: "Failed to add bookmark"
                    )
                }
        }
    }

    fun removeBookmark(ServiceId: String) {
        viewModelScope.launch {
            repository.unsaveService(ServiceId)
                .onSuccess {
                    _uiState.value = _uiState.value.copy(isSuccess = true)
                    loadSavedServices()
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

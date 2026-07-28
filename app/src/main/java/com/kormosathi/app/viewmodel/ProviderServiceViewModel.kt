package com.kormosathi.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kormosathi.app.model.ProviderService
import com.kormosathi.app.repository.ProviderServiceRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class ProviderServiceUiState(
    val isLoading: Boolean = false,
    val services: List<ProviderService> = emptyList(),
    val success: Boolean = false,
    val error: String? = null
)

class ProviderServiceViewModel : ViewModel() {

    private val repository = ProviderServiceRepository()

    private val _uiState = MutableStateFlow(ProviderServiceUiState())
    val uiState: StateFlow<ProviderServiceUiState> = _uiState.asStateFlow()

    fun loadProviderServices(providerId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            val list = repository.getProviderServices(providerId)

            _uiState.value = ProviderServiceUiState(
                services = list,
                isLoading = false
            )
        }
    }

    fun saveService(service: ProviderService) {
        viewModelScope.launch {

            _uiState.value = _uiState.value.copy(isLoading = true)

            val result = repository.saveService(service)

            _uiState.value = _uiState.value.copy(
                isLoading = false,
                success = result,
                error = if (result) null else "Failed to save service"
            )
        }
    }
}
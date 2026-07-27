package com.kormosathi.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kormosathi.app.model.Service
import com.kormosathi.app.repository.ServiceRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class ServiceUiState(
    val isLoading: Boolean = false,
    val services: List<Service> = emptyList(),
    val selectedService: Service? = null,
    val errorMessage: String = "",
    val searchTitle: String = "",
    val searchCategory: String = "",
    val searchDistrict: String = ""
)

class ServiceViewModel : ViewModel() {

    private val repository = ServiceRepository()

    private val _uiState = MutableStateFlow(ServiceUiState())
    val uiState: StateFlow<ServiceUiState> = _uiState.asStateFlow()

    init {
        loadAllServices()
    }

    private fun loadAllServices() {
        viewModelScope.launch {
            _uiState.value = ServiceUiState(isLoading = true)
            val Services = repository.getAllServices()
            _uiState.value = ServiceUiState(isLoading = false, services = Services)
        }
    }

    fun searchServices(title: String, category: String, district: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                searchTitle = title,
                searchCategory = category,
                searchDistrict = district
            )

            val Services = repository.searchServices(title, category, district)
            _uiState.value = ServiceUiState(
                isLoading = false,
                services = Services,
                searchTitle = title,
                searchCategory = category,
                searchDistrict = district
            )
        }
    }

    fun filterByDistrict(district: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, searchDistrict = district)

            val Services = repository.filterByDistrict(district)
            _uiState.value = ServiceUiState(
                isLoading = false,
                services = Services,
                searchDistrict = district
            )
        }
    }

    fun filterByCategory(category: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, searchCategory = category)

            val Services = repository.filterByCategory(category)
            _uiState.value = ServiceUiState(
                isLoading = false,
                services = Services,
                searchCategory = category
            )
        }
    }

    fun getServiceDetails(ServiceId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            val Service = repository.getServiceById(ServiceId)
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                selectedService = Service,
                errorMessage = if (Service == null) "Service not found" else ""
            )
        }
    }

    fun resetSearch() {
        loadAllServices()
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(errorMessage = "")
    }
}

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

    private val _uiState = MutableStateFlow(
        ServiceUiState()
    )

    val uiState: StateFlow<ServiceUiState> =
        _uiState.asStateFlow()

    init {
        loadAllServices()
    }

    private fun loadAllServices() {

        viewModelScope.launch {

            _uiState.value = _uiState.value.copy(
                isLoading = true,
                errorMessage = ""
            )

            val services = repository.getAllServices()

            _uiState.value = ServiceUiState(
                isLoading = false,
                services = services
            )
        }
    }

    fun searchServices(
        title: String,
        category: String,
        district: String
    ) {

        viewModelScope.launch {

            _uiState.value = _uiState.value.copy(
                isLoading = true,
                searchTitle = title,
                searchCategory = category,
                searchDistrict = district,
                errorMessage = ""
            )

            var services = repository.searchServices(
                title
            )

            if (category.isNotBlank()) {

                services = services.filter { service ->

                    service.category.equals(
                        category,
                        ignoreCase = true
                    )

                }
            }

            if (district.isNotBlank()) {

                services = services.filter { service ->

                    service.district.equals(
                        district,
                        ignoreCase = true
                    )

                }
            }

            _uiState.value = _uiState.value.copy(
                isLoading = false,
                services = services
            )
        }
    }

    fun filterByDistrict(
        district: String
    ) {

        viewModelScope.launch {

            _uiState.value = _uiState.value.copy(
                isLoading = true,
                searchDistrict = district,
                errorMessage = ""
            )

            val services = repository.filterByDistrict(
                district
            )

            _uiState.value = ServiceUiState(
                isLoading = false,
                services = services,
                searchDistrict = district
            )
        }
    }

    fun filterByCategory(
        category: String
    ) {

        viewModelScope.launch {

            _uiState.value = _uiState.value.copy(
                isLoading = true,
                searchCategory = category,
                errorMessage = ""
            )

            val services = repository.filterByCategory(
                category
            )

            _uiState.value = ServiceUiState(
                isLoading = false,
                services = services,
                searchCategory = category
            )
        }
    }

    fun getServiceDetails(
        serviceId: String
    ) {

        viewModelScope.launch {

            _uiState.value = _uiState.value.copy(
                isLoading = true,
                errorMessage = ""
            )

            val service = repository.getServiceById(
                serviceId
            )

            _uiState.value = _uiState.value.copy(
                isLoading = false,
                selectedService = service,
                errorMessage = if (service == null) {
                    "Service not found"
                } else {
                    ""
                }
            )
        }
    }

    fun resetSearch() {
        loadAllServices()
    }

    fun clearError() {

        _uiState.value = _uiState.value.copy(
            errorMessage = ""
        )
    }
}
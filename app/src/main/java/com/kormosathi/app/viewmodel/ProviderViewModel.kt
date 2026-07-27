package com.kormosathi.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kormosathi.app.model.Provider
import com.kormosathi.app.model.Service
import com.kormosathi.app.model.Booking
import com.kormosathi.app.repository.ProviderRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class ProviderUiState(
    val isLoading: Boolean = false,
    val provider: Provider? = null,
    val services: List<Service> = emptyList(),
    val applicants: List<Booking> = emptyList(),
    val isSuccess: Boolean = false,
    val errorMessage: String = ""
)

class ProviderViewModel : ViewModel() {

    private val repository = ProviderRepository()

    private val _uiState = MutableStateFlow(ProviderUiState())
    val uiState: StateFlow<ProviderUiState> = _uiState.asStateFlow()

    init {
        loadProviderProfile()
    }

    private fun loadProviderProfile() {
        viewModelScope.launch {
            _uiState.value = ProviderUiState(isLoading = true)

            val Provider = repository.getProviderProfile()
            if (Provider != null) {
                _uiState.value = ProviderUiState(
                    isLoading = false,
                    provider = Provider
                )
                loadProviderServices()
            } else {
                _uiState.value = ProviderUiState(
                    isLoading = false,
                    errorMessage = "Provider profile not found"
                )
            }
        }
    }

    fun createProviderProfile(provider: Provider) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            repository.createProviderProfile(provider)
                .onSuccess {
                    _uiState.value = ProviderUiState(
                        isLoading = false,
                        isSuccess = true,
                        provider = provider
                    )
                    loadProviderServices()
                }
                .onFailure { exception ->
                    _uiState.value = ProviderUiState(
                        isLoading = false,
                        errorMessage = exception.message ?: "Failed to create Provider profile"
                    )
                }
        }
    }

    fun postService(
        title: String,
        description: String,
        category: String,
        district: String,
        block: String,
        salary: Double
    ) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            val service = Service(
                ServiceId = "",
                title = title,
                description = description,
                category = category,
                district = district,
                block = block,
                salary = salary.toString(),
                ProviderUid = _uiState.value.provider?.uid ?: "",
                ProviderName = _uiState.value.provider?.companyName ?: "",
                phone = _uiState.value.provider?.phone ?: "",
                createdAt = System.currentTimeMillis(),
                status = "active"
            )

            repository.postService(service)
                .onSuccess {
                    _uiState.value = _uiState.value.copy(isSuccess = true, isLoading = false)
                    loadProviderServices()
                }
                .onFailure { exception ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = exception.message ?: "Failed to post Service"
                    )
                }
        }
    }

    fun postService(service: Service) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            repository.postService(service)
                .onSuccess {
                    _uiState.value = _uiState.value.copy(isSuccess = true, isLoading = false)
                    loadProviderServices()
                }
                .onFailure { exception ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = exception.message ?: "Failed to post Service"
                    )
                }
        }
    }

    fun updateService(
        ServiceId: String,
        title: String,
        description: String,
        category: String,
        district: String,
        block: String,
        salary: Double
    ) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            val existingService = _uiState.value.services.find { it.ServiceId == ServiceId }
            val service = existingService?.copy(
                title = title,
                description = description,
                category = category,
                district = district,
                block = block,
                salary = salary.toString()
            ) ?: Service(
                ServiceId = ServiceId,
                title = title,
                description = description,
                category = category,
                district = district,
                block = block,
                salary = salary.toString(),
                ProviderUid = _uiState.value.provider?.uid ?: "",
                ProviderName = _uiState.value.provider?.companyName ?: "",
                phone = _uiState.value.provider?.phone ?: "",
                createdAt = System.currentTimeMillis(),
                status = "active"
            )

            repository.editService(service)
                .onSuccess {
                    _uiState.value = _uiState.value.copy(isSuccess = true, isLoading = false)
                    loadProviderServices()
                }
                .onFailure { exception ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = exception.message ?: "Failed to edit Service"
                    )
                }
        }
    }

    fun editService(service: Service) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            repository.editService(service)
                .onSuccess {
                    _uiState.value = _uiState.value.copy(isSuccess = true, isLoading = false)
                    loadProviderServices()
                }
                .onFailure { exception ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = exception.message ?: "Failed to edit Service"
                    )
                }
        }
    }

    fun deleteService(ServiceId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            repository.deleteService(ServiceId)
                .onSuccess {
                    _uiState.value = _uiState.value.copy(isSuccess = true, isLoading = false)
                    loadProviderServices()
                }
                .onFailure { exception ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = exception.message ?: "Failed to delete Service"
                    )
                }
        }
    }

    private fun loadProviderServices() {
        viewModelScope.launch {
            val Services = repository.getProviderServices()
            _uiState.value = _uiState.value.copy(services = Services)
        }
    }

    fun getApplicants(ServiceId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            val applicants = repository.getServiceApplicants(ServiceId)
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                applicants = applicants
            )
        }
    }

    fun loadServiceApplicants(ServiceId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            val applicants = repository.getServiceApplicants(ServiceId)
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                applicants = applicants
            )
        }
    }

    fun acceptApplicant(ServiceId: String, BookingId: String) {
        viewModelScope.launch {
            repository.acceptApplicant(BookingId)
                .onSuccess {
                    _uiState.value = _uiState.value.copy(isSuccess = true)
                    getApplicants(ServiceId)
                }
                .onFailure { exception ->
                    _uiState.value = _uiState.value.copy(
                        errorMessage = exception.message ?: "Failed to accept applicant"
                    )
                }
        }
    }

    fun rejectApplicant(ServiceId: String, BookingId: String) {
        viewModelScope.launch {
            repository.rejectApplicant(BookingId)
                .onSuccess {
                    _uiState.value = _uiState.value.copy(isSuccess = true)
                    getApplicants(ServiceId)
                }
                .onFailure { exception ->
                    _uiState.value = _uiState.value.copy(
                        errorMessage = exception.message ?: "Failed to reject applicant"
                    )
                }
        }
    }

    fun clearSuccess() {
        _uiState.value = _uiState.value.copy(isSuccess = false)
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(errorMessage = "")
    }
}

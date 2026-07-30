package com.kormosathi.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.kormosathi.app.model.Booking
import com.kormosathi.app.model.Provider
import com.kormosathi.app.model.Service
import com.kormosathi.app.repository.BookingRepository
import com.kormosathi.app.repository.ProviderRepository
import com.kormosathi.app.repository.ServiceRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class ProviderUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String = "",
    val applicants: List<Booking> = emptyList(),

    // Provider profile
    val provider: Provider? = null,
    val providerChecked: Boolean = false
)

class ProviderViewModel : ViewModel() {

    private val providerRepository =
        ProviderRepository()

    private val bookingRepository =
        BookingRepository()

    private val serviceRepository =
        ServiceRepository()

    private val auth =
        FirebaseAuth.getInstance()

    private val _uiState =
        MutableStateFlow(
            ProviderUiState()
        )

    val uiState:
            StateFlow<ProviderUiState> =
        _uiState.asStateFlow()


    fun loadCurrentProvider() {

        val providerId =
            auth.currentUser?.uid

        if (providerId == null) {

            _uiState.value =
                _uiState.value.copy(
                    isLoading = false,
                    provider = null,
                    providerChecked = true
                )

            return
        }

        viewModelScope.launch {

            _uiState.value =
                _uiState.value.copy(
                    isLoading = true,
                    errorMessage = "",
                    providerChecked = false
                )

            val provider =
                providerRepository
                    .getProviderById(
                        providerId
                    )

            _uiState.value =
                _uiState.value.copy(
                    isLoading = false,
                    provider = provider,
                    providerChecked = true
                )
        }
    }


    fun saveProvider(
        provider: Provider
    ) {

        viewModelScope.launch {

            _uiState.value =
                _uiState.value.copy(
                    isLoading = true,
                    isSuccess = false,
                    errorMessage = ""
                )

            val result =
                providerRepository
                    .saveProvider(
                        provider
                    )

            _uiState.value =
                _uiState.value.copy(
                    isLoading = false,
                    isSuccess =
                        result.isSuccess,

                    provider =
                        if (result.isSuccess) {
                            provider
                        } else {
                            _uiState.value.provider
                        },

                    providerChecked = true,

                    errorMessage =
                        result
                            .exceptionOrNull()
                            ?.message
                            ?: ""
                )
        }
    }


    fun getApplicants(
        serviceId: String
    ) {

        viewModelScope.launch {

            _uiState.value =
                _uiState.value.copy(
                    isLoading = true,
                    errorMessage = ""
                )

            val applicants =
                bookingRepository
                    .getApplicantsByService(
                        serviceId
                    )

            _uiState.value =
                _uiState.value.copy(
                    isLoading = false,
                    applicants = applicants
                )
        }
    }


    fun acceptApplicant(
        serviceId: String,
        bookingId: String
    ) {

        viewModelScope.launch {

            _uiState.value =
                _uiState.value.copy(
                    isLoading = true,
                    isSuccess = false,
                    errorMessage = ""
                )

            val result =
                bookingRepository
                    .updateBookingStatus(
                        bookingId = bookingId,
                        status = "accepted"
                    )

            if (result.isSuccess) {

                getApplicants(
                    serviceId
                )

                _uiState.value =
                    _uiState.value.copy(
                        isSuccess = true
                    )

            } else {

                _uiState.value =
                    _uiState.value.copy(
                        isLoading = false,
                        errorMessage =
                            result
                                .exceptionOrNull()
                                ?.message
                                ?: "আবেদন গ্রহণ করা যায়নি"
                    )
            }
        }
    }


    fun rejectApplicant(
        serviceId: String,
        bookingId: String
    ) {

        viewModelScope.launch {

            _uiState.value =
                _uiState.value.copy(
                    isLoading = true,
                    isSuccess = false,
                    errorMessage = ""
                )

            val result =
                bookingRepository
                    .updateBookingStatus(
                        bookingId = bookingId,
                        status = "rejected"
                    )

            if (result.isSuccess) {

                getApplicants(
                    serviceId
                )

                _uiState.value =
                    _uiState.value.copy(
                        isSuccess = true
                    )

            } else {

                _uiState.value =
                    _uiState.value.copy(
                        isLoading = false,
                        errorMessage =
                            result
                                .exceptionOrNull()
                                ?.message
                                ?: "আবেদন প্রত্যাখ্যান করা যায়নি"
                    )
            }
        }
    }


    fun postService(
        service: Service
    ) {

        viewModelScope.launch {

            _uiState.value =
                _uiState.value.copy(
                    isLoading = true,
                    isSuccess = false,
                    errorMessage = ""
                )

            val providerId =
                auth.currentUser?.uid

            if (providerId == null) {

                _uiState.value =
                    _uiState.value.copy(
                        isLoading = false,
                        errorMessage =
                            "আপনি লগইন করেননি"
                    )

                return@launch
            }

            val result =
                serviceRepository
                    .postService(
                        service
                    )

            _uiState.value =
                _uiState.value.copy(
                    isLoading = false,
                    isSuccess =
                        result.isSuccess,

                    errorMessage =
                        result
                            .exceptionOrNull()
                            ?.message
                            ?: ""
                )
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

            _uiState.value =
                _uiState.value.copy(
                    isLoading = true,
                    isSuccess = false,
                    errorMessage = ""
                )

            val result =
                serviceRepository
                    .updateService(
                        serviceId =
                            ServiceId,

                        title =
                            title,

                        description =
                            description,

                        category =
                            category,

                        district =
                            district,

                        block =
                            block,

                        salary =
                            salary
                    )

            _uiState.value =
                _uiState.value.copy(
                    isLoading = false,
                    isSuccess =
                        result.isSuccess,

                    errorMessage =
                        result
                            .exceptionOrNull()
                            ?.message
                            ?: ""
                )
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
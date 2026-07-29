package com.kormosathi.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kormosathi.app.model.Booking
import com.kormosathi.app.model.Provider
import com.kormosathi.app.model.Service
import com.kormosathi.app.repository.ProviderRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


data class ProviderUiState(

    val isLoading: Boolean = false,

    val isSuccess: Boolean = false,

    val errorMessage: String = "",

    val applicants: List<Booking> = emptyList(),

    val services: List<Service> = emptyList()

)



class ProviderViewModel : ViewModel() {


    private val repository =
        ProviderRepository()



    private val _uiState =
        MutableStateFlow(
            ProviderUiState()
        )


    val uiState: StateFlow<ProviderUiState> =
        _uiState.asStateFlow()





    fun saveProvider(
        provider: Provider
    ) {


        viewModelScope.launch {


            _uiState.value =
                _uiState.value.copy(
                    isLoading = true,
                    errorMessage = ""
                )


            repository.saveProvider(provider)

                .onSuccess {


                    _uiState.value =
                        _uiState.value.copy(

                            isLoading = false,

                            isSuccess = true

                        )


                }


                .onFailure {


                    _uiState.value =
                        _uiState.value.copy(

                            isLoading = false,

                            errorMessage =
                                it.message
                                    ?: "Provider save failed"

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

                    errorMessage = ""

                )



            try {


                // Firebase save later

                val updatedServices =
                    _uiState.value.services.toMutableList()


                updatedServices.add(service)



                _uiState.value =
                    _uiState.value.copy(

                        isLoading = false,

                        isSuccess = true,

                        services = updatedServices

                    )


            }
            catch(e: Exception){


                _uiState.value =
                    _uiState.value.copy(

                        isLoading = false,

                        errorMessage =
                            e.message
                                ?: "Service post failed"

                    )


            }


        }


    }







    fun getApplicants(

        serviceId: String

    ) {


        viewModelScope.launch {


            _uiState.value =
                _uiState.value.copy(

                    isLoading = true

                )


            try {


                _uiState.value =
                    _uiState.value.copy(

                        isLoading = false,

                        applicants = emptyList()

                    )


            }
            catch(e: Exception){


                _uiState.value =
                    _uiState.value.copy(

                        isLoading = false,

                        errorMessage =
                            e.message
                                ?: "Applicants load failed"

                    )


            }


        }


    }







    fun acceptApplicant(

        serviceId: String,

        bookingId: String

    ) {


        viewModelScope.launch {


            _uiState.value =
                _uiState.value.copy(

                    isSuccess = true

                )


        }


    }







    fun rejectApplicant(

        serviceId: String,

        bookingId: String

    ) {


        viewModelScope.launch {


            _uiState.value =
                _uiState.value.copy(

                    isSuccess = true

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


            try {


                _uiState.value =
                    _uiState.value.copy(

                        isSuccess = true

                    )


            }
            catch(e: Exception){


                _uiState.value =
                    _uiState.value.copy(

                        errorMessage =
                            e.message
                                ?: "Update failed"

                    )


            }


        }


    }







    fun clearSuccess(){


        _uiState.value =
            _uiState.value.copy(

                isSuccess = false

            )


    }







    fun clearError(){


        _uiState.value =
            _uiState.value.copy(

                errorMessage = ""

            )


    }


}
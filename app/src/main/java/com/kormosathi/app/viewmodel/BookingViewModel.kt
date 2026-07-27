package com.kormosathi.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kormosathi.app.model.Booking
import com.kormosathi.app.repository.BookingRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class BookingUiState(
    val isLoading: Boolean = false,
    val Bookings: List<Booking> = emptyList(),
    val isSuccess: Boolean = false,
    val errorMessage: String = "",
    val hasApplied: Boolean = false
)

class BookingViewModel : ViewModel() {

    private val repository = BookingRepository()

    private val _uiState = MutableStateFlow(BookingUiState())
    val uiState: StateFlow<BookingUiState> = _uiState.asStateFlow()

    fun applyForService(
        ServiceId: String,
        applicantName: String,
        phone: String
    ) {
        viewModelScope.launch {
            _uiState.value = BookingUiState(isLoading = true)

            repository.applyForService(ServiceId, applicantName, phone)
                .onSuccess {
                    _uiState.value = BookingUiState(
                        isLoading = false,
                        isSuccess = true
                    )
                }
                .onFailure { exception ->
                    _uiState.value = BookingUiState(
                        isLoading = false,
                        errorMessage = exception.message ?: "Failed to apply for Service"
                    )
                }
        }
    }

    fun loadMyBookings() {
        viewModelScope.launch {
            _uiState.value = BookingUiState(isLoading = true)

            val Bookings = repository.getMyBookings()
            _uiState.value = BookingUiState(
                isLoading = false,
                Bookings = Bookings
            )
        }
    }

    fun checkIfApplied(ServiceId: String) {
        viewModelScope.launch {
            val hasApplied = repository.hasUserApplied(ServiceId)
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

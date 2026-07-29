package com.kormosathi.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kormosathi.app.model.Booking
import com.kormosathi.app.repository.BookingRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BookingViewModel : ViewModel() {

    private val repository = BookingRepository()

    private val _customerBookings =
        MutableStateFlow<List<Booking>>(emptyList())
    val customerBookings: StateFlow<List<Booking>> =
        _customerBookings

    private val _providerBookings =
        MutableStateFlow<List<Booking>>(emptyList())
    val providerBookings: StateFlow<List<Booking>> =
        _providerBookings

    private val _loading =
        MutableStateFlow(false)
    val loading: StateFlow<Boolean> =
        _loading

    fun createBooking(
        booking: Booking,
        onResult: (Boolean) -> Unit
    ) {

        viewModelScope.launch {

            _loading.value = true

            val result = repository.createBooking(booking)

            _loading.value = false

            onResult(result.isSuccess)

        }

    }

    fun loadCustomerBookings(
        customerId: String
    ) {

        viewModelScope.launch {

            _loading.value = true

            _customerBookings.value =
                repository.getCustomerBookings(customerId)

            _loading.value = false

        }

    }

    fun loadProviderBookings(
        providerId: String
    ) {

        viewModelScope.launch {

            _loading.value = true

            _providerBookings.value =
                repository.getProviderBookings(providerId)

            _loading.value = false

        }

    }

    fun updateStatus(
        bookingId: String,
        status: String,
        onResult: (Boolean) -> Unit
    ) {

        viewModelScope.launch {

            val result =
                repository.updateBookingStatus(
                    bookingId,
                    status
                )

            onResult(result.isSuccess)

        }

    }

}
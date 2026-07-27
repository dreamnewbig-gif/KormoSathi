package com.kormosathi.app.model

data class Booking(
    val BookingId: String = "",
    val ServiceId: String = "",
    val applicantUid: String = "",
    val applicantName: String = "",
    val phone: String = "",
    val appliedAt: Long = System.currentTimeMillis(),
    val status: String = "pending"
)

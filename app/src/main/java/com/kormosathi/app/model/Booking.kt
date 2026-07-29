package com.kormosathi.app.model

data class Booking(

    val id: String = "",

    val userId: String = "",

    val applicantName: String = "",

    val phone: String = "",

    val providerId: String = "",

    val serviceId: String = "",

    val bookingDate: Long = 0L,

    val bookingTime: String = "",

    val address: String = "",

    val status: String = "Pending",

    val totalAmount: Double = 0.0,

    val notes: String = "",

    val appliedAt: Long = 0L,

    val createdAt: Long = System.currentTimeMillis()

)

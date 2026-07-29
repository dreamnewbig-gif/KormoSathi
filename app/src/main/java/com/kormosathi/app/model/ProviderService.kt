package com.kormosathi.app.model

data class ProviderService(

    val id: String = "",

    val providerId: String = "",

    val serviceId: String = "",

    val price: Double = 0.0,

    val visitCharge: Double = 0.0,

    val workingRadius: Int = 10,

    val isActive: Boolean = true,

    val createdAt: Long = 0L

)
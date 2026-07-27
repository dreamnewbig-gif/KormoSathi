package com.kormosathi.app.model

data class Service(
    val ServiceId: String = "",
    val title: String = "",
    val description: String = "",
    val category: String = "",
    val district: String = "",
    val block: String = "",
    val salary: String = "",
    val ProviderUid: String = "",
    val ProviderName: String = "",
    val phone: String = "",
    val createdAt: Long = System.currentTimeMillis(),
    val status: String = "active"
)

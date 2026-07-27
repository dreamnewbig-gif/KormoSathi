package com.kormosathi.app.model

data class SavedService(
    val saveId: String = "",
    val userUid: String = "",
    val ServiceId: String = "",
    val ServiceTitle: String = "",
    val companyName: String = "",
    val district: String = "",
    val category: String = "",
    val salary: Double = 0.0,
    val savedAt: Long = System.currentTimeMillis()
)

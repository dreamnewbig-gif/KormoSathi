package com.kormosathi.app.model

data class SavedJob(
    val saveId: String = "",
    val userUid: String = "",
    val jobId: String = "",
    val jobTitle: String = "",
    val companyName: String = "",
    val district: String = "",
    val category: String = "",
    val salary: Double = 0.0,
    val savedAt: Long = System.currentTimeMillis()
)

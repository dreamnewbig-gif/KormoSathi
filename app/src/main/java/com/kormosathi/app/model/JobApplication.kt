package com.kormosathi.app.model

data class JobApplication(
    val applicationId: String = "",
    val jobId: String = "",
    val applicantUid: String = "",
    val applicantName: String = "",
    val phone: String = "",
    val appliedAt: Long = System.currentTimeMillis(),
    val status: String = "pending"
)

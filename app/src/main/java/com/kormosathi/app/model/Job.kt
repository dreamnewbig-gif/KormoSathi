package com.kormosathi.app.model

data class Job(
    val jobId: String = "",
    val title: String = "",
    val description: String = "",
    val category: String = "",
    val district: String = "",
    val block: String = "",
    val salary: String = "",
    val employerUid: String = "",
    val employerName: String = "",
    val phone: String = "",
    val createdAt: Long = System.currentTimeMillis(),
    val status: String = "active"
)

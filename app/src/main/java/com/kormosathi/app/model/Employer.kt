package com.kormosathi.app.model

data class Employer(
    val employerId: String = "",
    val uid: String = "",
    val companyName: String = "",
    val ownerName: String = "",
    val phone: String = "",
    val email: String = "",
    val address: String = "",
    val businessType: String = "",
    val createdAt: Long = System.currentTimeMillis(),
    val isApproved: Boolean = false,
    val status: String = "pending"
)

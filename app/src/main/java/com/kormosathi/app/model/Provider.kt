package com.kormosathi.app.model

data class Provider(

    val id: String = "",

    val userId: String = "",

    val fullName: String = "",

    val phone: String = "",

    val email: String = "",

    val profilePhoto: String = "",

    val gender: String = "",

    val dateOfBirth: String = "",

    val address: String = "",

    val district: String = "",

    val policeStationOrBlock: String = "",

    val postOffice: String = "",

    val areaType: String = "",

    val villageOrLocality: String = "",

    val state: String = "West Bengal",

    val pincode: String = "",

    val latitude: Double = 0.0,

    val longitude: Double = 0.0,

    val categoryIds: List<String> = emptyList(),

    val subCategoryIds: List<String> = emptyList(),

    val serviceItemIds: List<String> = emptyList(),

    val experienceYears: Int = 0,

    val about: String = "",

    val languages: List<String> = emptyList(),

    // Basic verification information
    val identityDocumentType: String = "",

    val identityDocumentLastFour: String = "",

    val licenceOrRegistrationType: String = "",

    val licenceOrRegistrationNumber: String = "",

    val verificationStatus: String = "Pending",

    val profileCompleted: Boolean = false,

    val isVerified: Boolean = false,

    val isApproved: Boolean = false,

    val isAvailable: Boolean = true,

    val rating: Double = 0.0,

    val totalReviews: Int = 0,

    val totalBookings: Int = 0,

    val createdAt: Long = 0L

)
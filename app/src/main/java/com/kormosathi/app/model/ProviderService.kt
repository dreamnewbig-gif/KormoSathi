package com.kormosathi.app.model

data class ProviderService(

    val id: String = "",

    val providerId: String = "",

    val categoryId: String = "",

    val subCategoryId: String = "",

    val serviceItemId: String = "",

    val serviceNameEn: String = "",

    val serviceNameBn: String = "",

    val price: Double = 0.0,

    val isAvailable: Boolean = true
)
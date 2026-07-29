package com.kormosathi.app.model

data class Service(

    val id: String = "",

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

    val createdAt: Long = 0L,

    val status: String = "",

    val categoryId: String = "",

    val subCategoryId: String = "",

    val nameEn: String = "",

    val nameBn: String = "",

    val descriptionEn: String = "",

    val descriptionBn: String = "",

    val icon: String = "",

    val banner: String = "",

    val basePrice: Double = 0.0,

    val unit: String = "",

    val order: Int = 0,

    val isActive: Boolean = true

)

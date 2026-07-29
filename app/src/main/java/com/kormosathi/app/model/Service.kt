package com.kormosathi.app.model

data class Service(

    val id: String = "",

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
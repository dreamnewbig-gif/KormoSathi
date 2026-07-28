package com.kormosathi.app.model

data class ServiceItem(

    val id: String = "",

    val categoryId: String = "",

    val subCategoryId: String = "",

    val nameEn: String = "",

    val nameBn: String = "",

    val icon: String = "",

    val order: Int = 0,

    val isActive: Boolean = true

)
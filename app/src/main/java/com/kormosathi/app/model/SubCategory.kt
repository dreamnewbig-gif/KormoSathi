package com.kormosathi.app.model

data class SubCategory(
    val id: String = "",
    val categoryId: String = "",
    val nameEn: String = "",
    val nameBn: String = "",
    val icon: String = "",
    val imageUrl: String = "",
    val order: Int = 0,
    val isActive: Boolean = true
)
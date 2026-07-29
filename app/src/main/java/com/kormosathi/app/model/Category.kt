package com.kormosathi.app.model

data class Category(

    val id: String = "",

    val nameEn: String = "",

    val nameBn: String = "",

    val imageUrl: String = "",

    val icon: String = "",

    val banner: String = "",

    val order: Int = 0,

    val isActive: Boolean = true
)
package com.kormosathi.app.model

data class User(
    val uid: String = "",
    val phone: String = "",
    val role: String = "customer",
    val name: String = "",
    val district: String = "",
    val area: String = "",
    val createdAt: Long = System.currentTimeMillis(),
    val profileCompleted: Boolean = false
)
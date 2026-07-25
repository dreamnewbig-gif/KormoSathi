package com.kormosathi.app.model

data class User(

    val uid: String = "",

    val phone: String = "",

    val role: String = "",

    val name: String = "",

    val district: String = "",

    val area: String = "",

    val createdAt: Long = System.currentTimeMillis()

)
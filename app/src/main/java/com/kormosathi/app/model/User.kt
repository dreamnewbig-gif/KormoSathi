package com.kormosathi.app.model

data class User(

    val id: String = "",

    val name: String = "",

    val phone: String = "",

    val email: String = "",

    val photo: String = "",

    val language: String = "bn",

    val savedAddresses: List<String> = emptyList(),

    val createdAt: Long = System.currentTimeMillis()

)
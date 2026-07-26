package com.kormosathi.app.model

data class UserProfile(

    val uid: String = "",

    val phone: String = "",

    val name: String = "",

    val gender: String = "",

    val age: Int = 0,

    val district: String = "",

    val block: String = "",

    val village: String = "",

    val pincode: String = "",

    val category: String = "",

    val experience: String = "",

    val expectedSalary: String = "",

    val profileImage: String = "",

    val role: String = "customer",

    val profileCompleted: Boolean = false

)
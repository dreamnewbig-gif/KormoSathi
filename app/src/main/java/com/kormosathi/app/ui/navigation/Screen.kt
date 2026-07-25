package com.kormosathi.app.ui.navigation

sealed class Screen(val route:String){

    object Splash:Screen("splash")
    object Welcome:Screen("welcome")

    object Login:Screen("login")
    object Otp:Screen("otp")

    object CustomerHome:Screen("customer_home")
    object WorkerHome:Screen("worker_home")

    object Profile:Screen("profile")
    object EditProfile:Screen("edit_profile")

    object Booking:Screen("booking")
}
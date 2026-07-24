package com.kormosathi.app.ui.navigation

sealed class Screen(val route: String) {

    object Splash : Screen("splash")

    object Welcome : Screen("welcome")

    object Login : Screen("login")

    object Home : Screen("home")
}
package com.kormosathi.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kormosathi.app.ui.screens.HomeScreen
import com.kormosathi.app.ui.screens.LoginScreen
import com.kormosathi.app.ui.screens.SplashScreen
import com.kormosathi.app.ui.screens.WelcomeScreen
import com.kormosathi.app.ui.screens.OtpScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {

        composable(Screen.Splash.route) {
            SplashScreen(navController)
        }

        composable(Screen.Welcome.route) {
            WelcomeScreen(navController)
        }

        composable(Screen.Login.route) {
            LoginScreen(navController)
        }

        composable(Screen.Otp.route) {
            OtpScreen()
        }

        composable(Screen.Home.route) {
            HomeScreen()
        }
    }
}
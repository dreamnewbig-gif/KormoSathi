package com.kormosathi.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kormosathi.app.ui.screens.ApplyServiceScreen
import com.kormosathi.app.ui.screens.EditServiceScreen
import com.kormosathi.app.ui.screens.ProviderDashboardScreen
import com.kormosathi.app.ui.screens.HomeScreen
import com.kormosathi.app.ui.screens.ServiceDetailsScreen
import com.kormosathi.app.ui.screens.ServiceListScreen
import com.kormosathi.app.ui.screens.LoginScreen
import com.kormosathi.app.ui.screens.MyBookingsScreen
import com.kormosathi.app.ui.screens.OtpScreen
import com.kormosathi.app.ui.screens.PostServiceScreen
import com.kormosathi.app.ui.screens.ProfileSetupScreen
import com.kormosathi.app.ui.screens.SavedServicesScreen
import com.kormosathi.app.ui.screens.SplashScreen
import com.kormosathi.app.ui.screens.ViewApplicantsScreen
import com.kormosathi.app.ui.screens.WelcomeScreen
import com.kormosathi.app.viewmodel.AuthViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(navController, authViewModel)
        }

        composable(Screen.Welcome.route) {
            WelcomeScreen(navController)
        }

        composable(Screen.Login.route) {
            LoginScreen(navController, authViewModel)
        }

        composable(Screen.Otp.route) {
            OtpScreen(navController, authViewModel)
        }

        composable(Screen.ProfileSetup.route) {
            ProfileSetupScreen(navController)
        }

        composable(Screen.Home.route) {
            HomeScreen(navController, authViewModel)
        }

        composable(Screen.ServiceList.route) {
            ServiceListScreen(navController)
        }

        composable(Screen.ServiceDetails.route) { backStackEntry ->
            val ServiceId = backStackEntry.arguments?.getString("ServiceId") ?: ""
            ServiceDetailsScreen(navController, ServiceId)
        }

        composable(Screen.ApplyService.route) { backStackEntry ->
            val ServiceId = backStackEntry.arguments?.getString("ServiceId") ?: ""
            ApplyServiceScreen(navController, ServiceId)
        }

        composable(Screen.MyBookings.route) {
            MyBookingsScreen(navController)
        }

        composable(Screen.ProviderDashboard.route) {
            ProviderDashboardScreen(navController)
        }

        composable(Screen.PostService.route) {
            PostServiceScreen(navController)
        }

        composable(Screen.EditService.route) { backStackEntry ->
            val ServiceId = backStackEntry.arguments?.getString("ServiceId") ?: ""
            EditServiceScreen(navController, ServiceId)
        }

        composable(Screen.ViewApplicants.route) { backStackEntry ->
            val ServiceId = backStackEntry.arguments?.getString("ServiceId") ?: ""
            ViewApplicantsScreen(navController, ServiceId)
        }

        composable(Screen.SavedServices.route) {
            SavedServicesScreen(navController)
        }
    }
}
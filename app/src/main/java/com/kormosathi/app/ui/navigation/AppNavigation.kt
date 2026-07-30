package com.kormosathi.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.kormosathi.app.ui.screens.CategoryScreen
import com.kormosathi.app.ui.screens.CategorySelectionScreen
import com.kormosathi.app.ui.screens.EditServiceScreen
import com.kormosathi.app.ui.screens.HomeScreen
import com.kormosathi.app.ui.screens.LoginScreen
import com.kormosathi.app.ui.screens.MyBookingsScreen
import com.kormosathi.app.ui.screens.OtpScreen
import com.kormosathi.app.ui.screens.PostServiceScreen
import com.kormosathi.app.ui.screens.ProfileScreen
import com.kormosathi.app.ui.screens.ProfileSetupScreen
import com.kormosathi.app.ui.screens.ProviderDashboardScreen
import com.kormosathi.app.ui.screens.ProviderListScreen
import com.kormosathi.app.ui.screens.ProviderRegistrationScreen
import com.kormosathi.app.ui.screens.SavedServicesScreen
import com.kormosathi.app.ui.screens.ServiceDetailsScreen
import com.kormosathi.app.ui.screens.ServiceItemScreen
import com.kormosathi.app.ui.screens.ServiceItemSelectionScreen
import com.kormosathi.app.ui.screens.ServiceListScreen
import com.kormosathi.app.ui.screens.SplashScreen
import com.kormosathi.app.ui.screens.SubCategoryScreen
import com.kormosathi.app.ui.screens.SubCategorySelectionScreen
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

            SplashScreen(
                navController = navController,
                authViewModel = authViewModel
            )
        }

        composable(Screen.Welcome.route) {

            WelcomeScreen(
                navController = navController
            )
        }

        composable(Screen.Login.route) {

            LoginScreen(
                navController = navController,
                authViewModel = authViewModel
            )
        }

        composable(Screen.Otp.route) {

            OtpScreen(
                navController = navController,
                authViewModel = authViewModel
            )
        }

        composable(Screen.ProfileSetup.route) {

            ProfileSetupScreen(
                navController = navController
            )
        }

        composable(Screen.Home.route) {

            HomeScreen(
                navController = navController,
                authViewModel = authViewModel
            )
        }

        composable(Screen.Profile.route) {

            ProfileScreen(
                navController = navController
            )
        }

        // Customer Category
        composable(Screen.Category.route) {

            CategoryScreen(
                onCategoryClick = { categoryId ->

                    navController.navigate(
                        Screen.SubCategory.createRoute(
                            categoryId
                        )
                    )
                }
            )
        }

        // Customer Subcategory
        composable(Screen.SubCategory.route) { backStackEntry ->

            val categoryId =
                backStackEntry.arguments
                    ?.getString("categoryId")
                    ?: ""

            SubCategoryScreen(
                categoryId = categoryId,
                navController = navController
            )
        }

        // Customer Service Items
        composable(Screen.ServiceItems.route) { backStackEntry ->

            val subCategoryId =
                backStackEntry.arguments
                    ?.getString("subCategoryId")
                    ?: ""

            ServiceItemScreen(
                subCategoryId = subCategoryId,
                navController = navController
            )
        }

        composable(Screen.ServiceList.route) {

            ServiceListScreen(
                navController = navController
            )
        }

        composable(Screen.ServiceDetails.route) { backStackEntry ->

            val serviceId =
                backStackEntry.arguments
                    ?.getString("serviceId")
                    ?: ""

            ServiceDetailsScreen(
                navController = navController,
                serviceId = serviceId
            )
        }

        composable(Screen.MyBookings.route) {

            MyBookingsScreen(
                navController = navController
            )
        }

        composable(Screen.SavedServices.route) {

            SavedServicesScreen(
                navController = navController
            )
        }

        // Provider Registration
        composable(Screen.ProviderRegistration.route) {

            ProviderRegistrationScreen(
                navController = navController
            )
        }

        // Provider Category Selection
        composable(Screen.CategorySelection.route) {

            CategorySelectionScreen(
                navController = navController
            )
        }

        // Provider Subcategory Selection
        composable(
            Screen.SubCategorySelection.route
        ) { backStackEntry ->

            val categoryId =
                backStackEntry.arguments
                    ?.getString("categoryId")
                    ?: ""

            SubCategorySelectionScreen(
                navController = navController,
                categoryId = categoryId
            )
        }

        // Provider Service Item Selection
        composable(
            Screen.ServiceItemSelection.route
        ) { backStackEntry ->

            val subCategory =
                backStackEntry.arguments
                    ?.getString("subCategory")
                    ?: ""

            ServiceItemSelectionScreen(
                navController = navController,
                subCategory = subCategory
            )
        }

        // Provider Dashboard
        composable(
            Screen.ProviderDashboard.route
        ) {

            ProviderDashboardScreen(
                navController = navController
            )
        }

        composable(Screen.PostService.route) {

            PostServiceScreen(
                navController = navController
            )
        }

        composable(Screen.EditService.route) { backStackEntry ->

            val serviceId =
                backStackEntry.arguments
                    ?.getString("serviceId")
                    ?: ""

            EditServiceScreen(
                navController = navController,
                ServiceId = serviceId
            )
        }

        // Customer Provider List
        composable(Screen.ProviderList.route) { backStackEntry ->

            val serviceName =
                backStackEntry.arguments
                    ?.getString("serviceName")
                    ?: ""

            ProviderListScreen(
                navController = navController,
                serviceName = serviceName
            )
        }
    }
}
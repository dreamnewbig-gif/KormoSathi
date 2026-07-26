package com.kormosathi.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kormosathi.app.ui.screens.ApplyJobScreen
import com.kormosathi.app.ui.screens.EditJobScreen
import com.kormosathi.app.ui.screens.EmployerDashboardScreen
import com.kormosathi.app.ui.screens.HomeScreen
import com.kormosathi.app.ui.screens.JobDetailsScreen
import com.kormosathi.app.ui.screens.JobListScreen
import com.kormosathi.app.ui.screens.LoginScreen
import com.kormosathi.app.ui.screens.MyApplicationsScreen
import com.kormosathi.app.ui.screens.OtpScreen
import com.kormosathi.app.ui.screens.PostJobScreen
import com.kormosathi.app.ui.screens.ProfileSetupScreen
import com.kormosathi.app.ui.screens.SavedJobsScreen
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

        composable(Screen.JobList.route) {
            JobListScreen(navController)
        }

        composable(Screen.JobDetails.route) { backStackEntry ->
            val jobId = backStackEntry.arguments?.getString("jobId") ?: ""
            JobDetailsScreen(navController, jobId)
        }

        composable(Screen.ApplyJob.route) { backStackEntry ->
            val jobId = backStackEntry.arguments?.getString("jobId") ?: ""
            ApplyJobScreen(navController, jobId)
        }

        composable(Screen.MyApplications.route) {
            MyApplicationsScreen(navController)
        }

        composable(Screen.EmployerDashboard.route) {
            EmployerDashboardScreen(navController)
        }

        composable(Screen.PostJob.route) {
            PostJobScreen(navController)
        }

        composable(Screen.EditJob.route) { backStackEntry ->
            val jobId = backStackEntry.arguments?.getString("jobId") ?: ""
            EditJobScreen(navController, jobId)
        }

        composable(Screen.ViewApplicants.route) { backStackEntry ->
            val jobId = backStackEntry.arguments?.getString("jobId") ?: ""
            ViewApplicantsScreen(navController, jobId)
        }

        composable(Screen.SavedJobs.route) {
            SavedJobsScreen(navController)
        }
    }
}
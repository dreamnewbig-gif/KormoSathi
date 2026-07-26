package com.kormosathi.app.ui.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Welcome : Screen("welcome")
    object Login : Screen("login")
    object Otp : Screen("otp")
    object ProfileSetup : Screen("profile_setup")
    object Home : Screen("home")
    object JobList : Screen("job_list")
    object JobDetails : Screen("job_details/{jobId}") {
        fun createRoute(jobId: String) = "job_details/$jobId"
    }
    object ApplyJob : Screen("apply_job/{jobId}") {
        fun createRoute(jobId: String) = "apply_job/$jobId"
    }
    object MyApplications : Screen("my_applications")

    object CustomerHome : Screen("customer_home")
    object WorkerHome : Screen("worker_home")
    object Profile : Screen("profile")
    object EditProfile : Screen("edit_profile")
    object Booking : Screen("booking")
}
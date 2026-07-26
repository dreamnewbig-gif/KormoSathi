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
    
    // Employer Routes
    object EmployerDashboard : Screen("employer_dashboard")
    object PostJob : Screen("post_job")
    object EditJob : Screen("edit_job/{jobId}") {
        fun createRoute(jobId: String) = "edit_job/$jobId"
    }
    object ViewApplicants : Screen("view_applicants/{jobId}") {
        fun createRoute(jobId: String) = "view_applicants/$jobId"
    }
    
    // Saved Jobs
    object SavedJobs : Screen("saved_jobs")

    object CustomerHome : Screen("customer_home")
    object WorkerHome : Screen("worker_home")
    object Profile : Screen("profile")
    object EditProfile : Screen("edit_profile")
    object Booking : Screen("booking")
}
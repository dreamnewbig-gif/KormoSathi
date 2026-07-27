package com.kormosathi.app.ui.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Welcome : Screen("welcome")
    object Login : Screen("login")
    object Otp : Screen("otp")
    object ProfileSetup : Screen("profile_setup")
    object Home : Screen("home")
    object ServiceList : Screen("Service_list")
    object ServiceDetails : Screen("Service_details/{ServiceId}") {
        fun createRoute(ServiceId: String) = "Service_details/$ServiceId"
    }
    object ApplyService : Screen("apply_Service/{ServiceId}") {
        fun createRoute(ServiceId: String) = "apply_Service/$ServiceId"
    }
    object MyBookings : Screen("my_Bookings")
    
    // Provider Routes
    object ProviderDashboard : Screen("Provider_dashboard")
    object PostService : Screen("post_Service")
    object EditService : Screen("edit_Service/{ServiceId}") {
        fun createRoute(ServiceId: String) = "edit_Service/$ServiceId"
    }
    object ViewApplicants : Screen("view_applicants/{ServiceId}") {
        fun createRoute(ServiceId: String) = "view_applicants/$ServiceId"
    }
    
    // Saved Services
    object SavedServices : Screen("saved_Services")

    object CustomerHome : Screen("customer_home")
    object WorkerHome : Screen("worker_home")
    object Profile : Screen("profile")
    object EditProfile : Screen("edit_profile")
    object Booking : Screen("booking")
}
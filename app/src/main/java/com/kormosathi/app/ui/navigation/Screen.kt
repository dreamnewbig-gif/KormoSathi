package com.kormosathi.app.ui.navigation

sealed class Screen(
    val route: String
) {

    object Splash :
        Screen("splash")

    object Welcome :
        Screen("welcome")

    object Login :
        Screen("login")

    object Otp :
        Screen("otp")

    object Home :
        Screen("home")

    object Category :
        Screen("category")

    object SubCategory :
        Screen(
            "subcategory/{categoryId}"
        ) {

        fun createRoute(
            categoryId: String
        ): String {

            return "subcategory/$categoryId"

        }

    }

    object ServiceList :
        Screen(
            "services/{subCategoryId}"
        ) {

        fun createRoute(
            subCategoryId: String
        ): String {

            return "services/$subCategoryId"

        }

    }

    object ServiceDetails :
        Screen(
            "service_details/{serviceId}"
        ) {

        fun createRoute(
            serviceId: String
        ): String {

            return "service_details/$serviceId"

        }

    }

    object Booking :
        Screen(
            "booking/{serviceId}"
        ) {

        fun createRoute(
            serviceId: String
        ): String {

            return "booking/$serviceId"

        }

    }

    object ServiceItems :
        Screen(
            "service_items/{subCategoryId}"
        ) {

        fun createRoute(
            subCategoryId: String
        ): String {

            return "service_items/$subCategoryId"

        }

    }

    object MyBookings :
        Screen("my_bookings")

    object SavedServices :
        Screen("saved_services")

    object Profile :
        Screen("profile")

    object ProfileSetup :
        Screen("profile_setup")

    object ProviderDashboard :
        Screen("provider_dashboard")

    object ProviderRegistration :
        Screen("provider_registration")

    object CategorySelection :
        Screen("category_selection")

    object SubCategorySelection :
        Screen(
            "subcategory_selection/{categoryId}"
        ) {

        fun createRoute(
            categoryId: String
        ): String {

            return "subcategory_selection/$categoryId"

        }

    }

    object ServiceItemSelection :
        Screen(
            "service_item_selection/{subCategory}"
        ) {

        fun createRoute(
            subCategory: String
        ): String {

            return "service_item_selection/$subCategory"

        }

    }

    object ProviderList :
        Screen(
            "provider_list/{serviceName}"
        ) {

        fun createRoute(
            serviceName: String
        ): String {

            return "provider_list/$serviceName"

        }

    }

    object PostService :
        Screen("post_service")

    object EditService :
        Screen(
            "edit_service/{serviceId}"
        ) {

        fun createRoute(
            serviceId: String
        ): String {

            return "edit_service/$serviceId"

        }

    }

    object BookingRequests :
        Screen("booking_requests")

    object AdminDashboard :
        Screen("admin_dashboard")

    object ManageUsers :
        Screen("manage_users")

    object ManageProviders :
        Screen("manage_providers")

    object ManageBookings :
        Screen("manage_bookings")

    object ManageCategories :
        Screen("manage_categories")

}
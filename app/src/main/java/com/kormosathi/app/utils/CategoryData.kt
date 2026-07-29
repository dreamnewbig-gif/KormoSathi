package com.kormosathi.app.utils

import com.kormosathi.app.model.Category

object CategoryData {

    val categories = listOf(

        Category(
            id = "home_services",
            nameEn = "Home Services",
            nameBn = "হোম সার্ভিস",
            icon = "home",
            imageUrl = "",
            order = 1
        ),

        Category(
            id = "repair_installation",
            nameEn = "Repair & Installation",
            nameBn = "রিপেয়ার ও ইনস্টলেশন",
            icon = "build",
            imageUrl = "",
            order = 2
        ),

        Category(
            id = "cleaning",
            nameEn = "Cleaning Services",
            nameBn = "পরিষ্কার-পরিচ্ছন্নতা",
            icon = "cleaning_services",
            imageUrl = "",
            order = 3
        ),

        Category(
            id = "vehicle",
            nameEn = "Vehicle Services",
            nameBn = "যানবাহন সার্ভিস",
            icon = "directions_car",
            imageUrl = "",
            order = 4
        ),

        Category(
            id = "personal",
            nameEn = "Personal Services",
            nameBn = "ব্যক্তিগত সার্ভিস",
            icon = "person",
            imageUrl = "",
            order = 5
        ),

        Category(
            id = "education",
            nameEn = "Education",
            nameBn = "শিক্ষা",
            icon = "school",
            imageUrl = "",
            order = 6
        ),

        Category(
            id = "events",
            nameEn = "Events",
            nameBn = "ইভেন্ট",
            icon = "celebration",
            imageUrl = "",
            order = 7
        ),

        Category(
            id = "business",
            nameEn = "Business & Digital",
            nameBn = "ব্যবসা ও ডিজিটাল",
            icon = "business",
            imageUrl = "",
            order = 8
        ),

        Category(
            id = "health",
            nameEn = "Health",
            nameBn = "স্বাস্থ্য",
            icon = "health_and_safety",
            imageUrl = "",
            order = 9
        ),

        Category(
            id = "travel",
            nameEn = "Travel",
            nameBn = "ভ্রমণ",
            icon = "travel_explore",
            imageUrl = "",
            order = 10
        ),

        Category(
            id = "outdoor",
            nameEn = "Outdoor Services",
            nameBn = "বাহিরের সার্ভিস",
            icon = "park",
            imageUrl = "",
            order = 11
        )

    )


    val subCategories = mapOf(

        "home_services" to listOf(
            "Electrician",
            "Plumber",
            "Carpenter",
            "Painter",
            "Mason"
        ),

        "repair_installation" to listOf(
            "AC Repair",
            "RO Repair",
            "Computer Repair",
            "Laptop Repair",
            "CCTV"
        ),

        "cleaning" to listOf(
            "Home Cleaning",
            "Bathroom Cleaning",
            "Kitchen Cleaning",
            "Office Cleaning"
        ),

        "vehicle" to listOf(
            "Car Repair",
            "Bike Repair",
            "Car Washing",
            "Bike Washing"
        ),

        "personal" to listOf(
            "Beauty Service",
            "Salon Service",
            "Massage Service"
        ),

        "education" to listOf(
            "Home Tutor",
            "Computer Tutor",
            "Music Teacher"
        ),

        "events" to listOf(
            "Photographer",
            "Videographer",
            "Event Decoration",
            "Catering"
        ),

        "business" to listOf(
            "Graphic Design",
            "Web Design",
            "Digital Marketing",
            "Social Media Management"
        ),

        "health" to listOf(
            "Nurse",
            "Physiotherapist",
            "Health Care Assistant"
        ),

        "travel" to listOf(
            "Travel Guide",
            "Tour Planning",
            "Cab Service"
        ),

        "outdoor" to listOf(
            "Gardening",
            "Tree Cutting",
            "Pest Control"
        )

    )


    val services = mapOf(

        "Electrician" to listOf(
            "Fan Installation",
            "Switch Repair",
            "Light Installation",
            "House Wiring",
            "MCB Repair",
            "Inverter Wiring"
        ),

        "Plumber" to listOf(
            "Tap Repair",
            "Pipe Repair",
            "Bathroom Fitting",
            "Water Tank Installation"
        ),

        "Carpenter" to listOf(
            "Furniture Repair",
            "Door Repair",
            "Window Repair",
            "Custom Furniture"
        ),

        "Painter" to listOf(
            "House Painting",
            "Wall Painting",
            "Waterproofing"
        ),

        "Mason" to listOf(
            "Brick Work",
            "Plaster Work",
            "Tiles Work"
        ),

        "AC Repair" to listOf(
            "AC Installation",
            "AC Repair",
            "AC Gas Charging",
            "AC Cleaning"
        ),

        "RO Repair" to listOf(
            "RO Installation",
            "RO Service",
            "RO Filter Change"
        ),

        "Computer Repair" to listOf(
            "Computer Formatting",
            "Desktop Repair",
            "Software Installation"
        ),

        "Laptop Repair" to listOf(
            "Laptop Formatting",
            "Laptop Screen Repair",
            "Laptop Keyboard Repair"
        ),

        "CCTV" to listOf(
            "CCTV Installation",
            "CCTV Repair",
            "CCTV Maintenance"
        ),

        "Graphic Design" to listOf(
            "Banner Design",
            "Poster Design",
            "Logo Design",
            "Visiting Card Design",
            "Social Media Post Design"
        ),

        "Web Design" to listOf(
            "Business Website",
            "Portfolio Website",
            "E-Commerce Website"
        ),

        "Digital Marketing" to listOf(
            "Facebook Marketing",
            "Google Ads",
            "SEO Service"
        ),

        "Social Media Management" to listOf(
            "Facebook Page Management",
            "Instagram Management",
            "Content Planning"
        ),

        "Photographer" to listOf(
            "Wedding Photography",
            "Event Photography",
            "Product Photography"
        ),

        "Videographer" to listOf(
            "Wedding Video",
            "Event Video",
            "Promotional Video"
        )

    )


    fun getSubCategories(
        categoryId: String
    ): List<String> {

        return subCategories[categoryId]
            ?: emptyList()

    }


    fun getServices(
        subCategory: String
    ): List<String> {

        return services[subCategory]
            ?: emptyList()

    }

}
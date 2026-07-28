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

}
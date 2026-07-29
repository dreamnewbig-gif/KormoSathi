package com.kormosathi.app.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun SubCategoryScreen(
    categoryId: String,
    navController: NavHostController
) {

    // Temporary data
    val subCategories = when (categoryId) {

        "home_services" -> listOf(
            "Electrician",
            "Plumber",
            "Carpenter",
            "Painter",
            "Mason",
            "Tiles Work"
        )

        "repair_installation" -> listOf(
            "AC Repair",
            "RO Repair",
            "CCTV",
            "Computer Repair",
            "Home Appliances"
        )

        "cleaning" -> listOf(
            "Home Cleaning",
            "Bathroom Cleaning",
            "Kitchen Cleaning",
            "Sofa Cleaning"
        )

        "vehicle" -> listOf(
            "Car Repair",
            "Bike Repair",
            "Car Wash",
            "Driver"
        )

        "personal" -> listOf(
            "Beauty",
            "Salon"
        )

        "education" -> listOf(
            "Tutor"
        )

        "events" -> listOf(
            "Photographer",
            "Event Decoration"
        )

        "business" -> listOf(
            "Graphic Designer",
            "Video Editor",
            "Digital Marketing"
        )

        "health" -> listOf(
            "Blood Test",
            "Nurse",
            "Physiotherapy"
        )

        "travel" -> listOf(
            "Bus Booking"
        )

        "outdoor" -> listOf(
            "Gardening",
            "House Shifting"
        )

        else -> emptyList()
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        items(subCategories) { item ->

            Card(
                modifier = Modifier
                    .clickable {

                        navController.navigate(
                            Screen.ServiceItems.createRoute(item)
                        )

                    },
                elevation = CardDefaults.cardElevation(4.dp)
            ) {

                Text(
                    text = item,
                    modifier = Modifier.padding(20.dp),
                    style = MaterialTheme.typography.titleMedium
                )

            }

        }

    }

}
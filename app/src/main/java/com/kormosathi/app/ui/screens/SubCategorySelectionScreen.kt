package com.kormosathi.app.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.kormosathi.app.ui.navigation.Screen

@Composable
fun SubCategorySelectionScreen(
    navController: NavHostController,
    categoryId: String
) {

    val subCategories = when (categoryId) {

        "home_services" -> listOf(
            "Electrician",
            "Plumber",
            "Carpenter",
            "Painter",
            "Mason",
            "Tiles Work",
            "False Ceiling"
        )

        "repair_installation" -> listOf(
            "AC Repair",
            "AC Installation",
            "RO Repair",
            "Computer Repair",
            "Laptop Repair",
            "CCTV Installation",
            "Appliance Repair"
        )

        "cleaning" -> listOf(
            "Home Cleaning",
            "Office Cleaning",
            "Bathroom Cleaning",
            "Kitchen Cleaning",
            "Sofa Cleaning",
            "Water Tank Cleaning"
        )

        "vehicle" -> listOf(
            "Bike Repair",
            "Car Repair",
            "Car Washing",
            "Bike Washing",
            "Tyre Service",
            "Battery Service"
        )

        "personal" -> listOf(
            "Beauty Service",
            "Salon Service",
            "Makeup Artist",
            "Massage Service",
            "Personal Trainer"
        )

        "education" -> listOf(
            "Home Tutor",
            "Computer Training",
            "Spoken English",
            "Music Teacher",
            "Dance Teacher"
        )

        "events" -> listOf(
            "Photographer",
            "Videographer",
            "Event Decoration",
            "Catering",
            "DJ Service",
            "Event Planning"
        )

        "business" -> listOf(
            "Graphic Design",
            "Web Development",
            "Digital Marketing",
            "Social Media Management",
            "Printing Service",
            "Logo Design"
        )

        "health" -> listOf(
            "Home Nursing",
            "Physiotherapy",
            "Doctor Consultation",
            "Medical Assistance"
        )

        "travel" -> listOf(
            "Car Rental",
            "Taxi Service",
            "Tour Guide",
            "Travel Booking"
        )

        "outdoor" -> listOf(
            "Gardening",
            "Pest Control",
            "Security Service",
            "Outdoor Cleaning"
        )

        else -> emptyList()

    }

    var selectedSubCategory by remember(categoryId) {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "সাব-ক্যাটাগরি নির্বাচন করুন",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "আপনার দেওয়া সার্ভিসের ধরন নির্বাচন করুন",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        if (subCategories.isEmpty()) {

            Text(
                text = "এই ক্যাটাগরিতে এখন কোনো সাব-ক্যাটাগরি নেই"
            )

        } else {

            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {

                items(
                    items = subCategories,
                    key = { item -> item }
                ) { item ->

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {

                                selectedSubCategory = item

                            }
                            .padding(
                                vertical = 14.dp
                            ),

                        verticalAlignment =
                            Alignment.CenterVertically,

                        horizontalArrangement =
                            Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = item,
                            style =
                                MaterialTheme
                                    .typography
                                    .titleMedium
                        )

                        Checkbox(
                            checked =
                                selectedSubCategory == item,

                            onCheckedChange = {

                                selectedSubCategory =
                                    if (it) {
                                        item
                                    } else {
                                        ""
                                    }

                            }
                        )

                    }

                    HorizontalDivider()

                }

            }

        }

        Button(
            onClick = {

                if (
                    selectedSubCategory.isNotBlank()
                ) {

                    navController.navigate(

                        Screen
                            .ServiceItemSelection
                            .createRoute(
                                selectedSubCategory
                            )

                    )

                }

            },

            enabled =
                selectedSubCategory.isNotBlank(),

            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)

        ) {

            Text(
                text = "Continue"
            )

        }

    }

}
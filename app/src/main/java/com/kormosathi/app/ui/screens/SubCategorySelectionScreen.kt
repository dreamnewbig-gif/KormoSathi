package com.kormosathi.app.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun SubCategorySelectionScreen(
    navController: NavHostController
) {

    val subCategories = listOf(
        "Electrician",
        "Plumber",
        "Carpenter",
        "Painter",
        "Mason",
        "Tiles Work",
        "AC Repair",
        "RO Repair",
        "Computer Repair",
        "CCTV",
        "Cleaning",
        "Beauty",
        "Salon",
        "Tutor",
        "Photographer",
        "Graphic Designer"
    )

    val selected = remember { mutableStateListOf<String>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            "Select Sub Categories",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {

            items(subCategories) { item ->

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {

                            if (selected.contains(item))
                                selected.remove(item)
                            else
                                selected.add(item)

                        }
                        .padding(vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(item)

                    Checkbox(
                        checked = selected.contains(item),
                        onCheckedChange = {

                            if (selected.contains(item))
                                selected.remove(item)
                            else
                                selected.add(item)

                        }
                    )

                }

                HorizontalDivider()

            }

        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {

                navController.navigate(Screen.ServiceItemSelection.route)

            }
        ) {
            Text("Continue")
        }

    }

}
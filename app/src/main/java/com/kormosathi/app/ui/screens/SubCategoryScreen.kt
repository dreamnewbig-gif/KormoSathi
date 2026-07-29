package com.kormosathi.app.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.kormosathi.app.ui.navigation.Screen


@Composable
fun SubCategoryScreen(
    categoryId: String,
    navController: NavHostController
) {


    val subCategories = when(categoryId) {


        "home_services" -> listOf(

            "Electrician",
            "Plumber",
            "Carpenter",
            "Painter",
            "Mason",
            "Tiles Work"

        )



        "repair_installation" -> listOf(

            "Fan Installation",
            "Switch Repair",
            "Light Installation",
            "AC Repair",
            "RO Repair",
            "Appliance Repair"

        )



        "business" -> listOf(

            "Graphic Design",
            "Video Editing",
            "Digital Marketing"

        )



        "vehicle" -> listOf(

            "Car Repair",
            "Bike Repair",
            "Car Wash",
            "Driver Service"

        )



        "cleaning" -> listOf(

            "Home Cleaning",
            "Bathroom Cleaning",
            "Kitchen Cleaning",
            "Sofa Cleaning"

        )



        "personal" -> listOf(

            "Beauty Service",
            "Salon",
            "Makeup Artist"

        )



        "education" -> listOf(

            "Home Tutor",
            "Online Tutor"

        )



        "health" -> listOf(

            "Blood Test",
            "Nurse",
            "Physiotherapy"

        )



        else -> emptyList()


    }





    Column(

        modifier = Modifier

            .fillMaxSize()

            .padding(16.dp)

    ) {



        Text(

            text = "Sub Categories",

            style = MaterialTheme.typography.headlineSmall

        )



        Spacer(

            modifier = Modifier.height(16.dp)

        )





        LazyVerticalGrid(

            columns = GridCells.Fixed(2),


            verticalArrangement = Arrangement.spacedBy(12.dp),


            horizontalArrangement = Arrangement.spacedBy(12.dp)


        ) {



            items(subCategories) { item ->




                Card(


                    modifier = Modifier

                        .fillMaxWidth()

                        .clickable {


                            navController.navigate(

                                Screen.ServiceItems.createRoute(

                                    "$categoryId|$item"

                                )

                            )


                        },


                    elevation = CardDefaults.cardElevation(4.dp)


                ) {



                    Text(

                        text = item,


                        modifier = Modifier

                            .padding(20.dp),


                        style = MaterialTheme.typography.titleMedium


                    )



                }



            }



        }



    }



}
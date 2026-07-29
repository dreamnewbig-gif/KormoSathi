package com.kormosathi.app.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.kormosathi.app.ui.navigation.Screen


@Composable
fun ServiceItemScreen(
    subCategoryId: String,
    navController: NavHostController
) {


    val services = when(subCategoryId) {


        "Electrician" -> listOf(
            "Fan Installation",
            "Switch Repair",
            "Light Installation",
            "House Wiring",
            "MCB Repair",
            "Inverter Wiring"
        )


        "Home Tutor" -> listOf(
            "Service Available Soon"
        )


        "Car Repair" -> listOf(
            "Engine Repair",
            "Brake Repair",
            "Car Servicing",
            "AC Repair"
        )


        "Bike Repair" -> listOf(
            "Engine Service",
            "Oil Change",
            "Brake Repair"
        )


        "Graphic Design" -> listOf(
            "Logo Design",
            "Banner Design",
            "Poster Design",
            "Visiting Card Design"
        )


        else -> listOf(
            "Service Available Soon"
        )

    }



    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)

    ) {


        Text(

            text = subCategoryId,

            style = MaterialTheme.typography.headlineSmall

        )


        Spacer(
            modifier = Modifier.height(16.dp)
        )



        LazyColumn(

            verticalArrangement = Arrangement.spacedBy(12.dp)

        ) {



            items(services) { service ->



                Card(

                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {


                            if(service != "Service Available Soon") {


                                navController.navigate(
                                    Screen.ProviderList.createRoute(service)
                                )


                            }


                        },


                    elevation = CardDefaults.cardElevation(4.dp)

                ) {



                    Text(

                        text = service,

                        modifier = Modifier.padding(18.dp),

                        style = MaterialTheme.typography.titleMedium

                    )


                }



            }



        }



    }



}
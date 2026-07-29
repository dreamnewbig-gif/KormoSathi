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
import com.kormosathi.app.ui.navigation.Screen

@Composable
fun ServiceItemSelectionScreen(
    navController: NavHostController
) {

    val services = listOf(

        "Fan Installation",
        "Switch Repair",
        "Light Installation",
        "House Wiring",
        "MCB Repair",
        "Inverter Wiring",
        "Water Purifier Installation",
        "RO Service",
        "AC Installation",
        "AC Gas Charging",
        "Computer Formatting",
        "Laptop Repair",
        "CCTV Installation",
        "Door Lock Repair",
        "Tiles Installation",
        "False Ceiling"

    )

    val selected = remember { mutableStateListOf<String>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            "Select Services",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {

            items(services) { service ->

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {

                            if (selected.contains(service))
                                selected.remove(service)
                            else
                                selected.add(service)

                        }
                        .padding(vertical = 12.dp),

                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(service)

                    Checkbox(
                        checked = selected.contains(service),
                        onCheckedChange = {

                            if (selected.contains(service))
                                selected.remove(service)
                            else
                                selected.add(service)

                        }
                    )

                }

                HorizontalDivider()

            }

        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                navController.navigate(Screen.ProviderList.route)
            }
        ) {
            Text("Finish")
        }

    }

}
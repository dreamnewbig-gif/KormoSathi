package com.kormosathi.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.kormosathi.app.ui.navigation.Screen

@Composable
fun ServiceDetailsScreen(
    navController: NavHostController,
    serviceId: String
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    "Service Banner",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        Spacer(Modifier.height(20.dp))

        Text(
            "House Electrical Repair",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(10.dp))

        Text(
            "Professional electrician for home wiring, switch, fan, light, MCB, inverter and all electrical works."
        )

        Spacer(Modifier.height(20.dp))

        ElevatedCard(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text("Starting Price")

                Text(
                    "₹299",
                    style = MaterialTheme.typography.headlineSmall
                )

                Spacer(Modifier.height(12.dp))

                Text("Estimated Duration")

                Text("30 - 60 Minutes")

                Spacer(Modifier.height(12.dp))

                Text("Rating")

                Text("⭐ 4.9 (245 Reviews)")

            }

        }

        Spacer(Modifier.height(20.dp))

        Text(
            "What's Included",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(10.dp))

        Text("✔ Inspection")

        Text("✔ Labour")

        Text("✔ Safety Check")

        Text("✔ Service Warranty")

        Spacer(Modifier.height(30.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {

                navController.navigate(Screen.ProviderList.route)

            }
        ) {

            Text("Book Now")

        }

    }

}
package com.kormosathi.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun BookingScreen(
    navController: NavHostController
) {

    var customerName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var bookingDate by remember { mutableStateOf("") }
    var bookingTime by remember { mutableStateOf("") }
    var problem by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        Text(
            "Book Service",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(Modifier.height(20.dp))

        OutlinedTextField(
            value = customerName,
            onValueChange = { customerName = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Full Name") }
        )

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Phone Number") }
        )

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = address,
            onValueChange = { address = it },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3,
            label = { Text("Service Address") }
        )

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = bookingDate,
            onValueChange = { bookingDate = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Preferred Date") }
        )

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = bookingTime,
            onValueChange = { bookingTime = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Preferred Time") }
        )

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = problem,
            onValueChange = { problem = it },
            modifier = Modifier.fillMaxWidth(),
            minLines = 5,
            label = { Text("Describe Your Problem") }
        )

        Spacer(Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text("Estimated Visit Charge")

                Spacer(Modifier.height(6.dp))

                Text(
                    "₹299",
                    style = MaterialTheme.typography.headlineSmall
                )

                Spacer(Modifier.height(6.dp))

                Text(
                    "Final amount may vary after inspection."
                )

            }
        }

        Spacer(Modifier.height(24.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {

                // TODO
                // Save Booking to Firestore

            }
        ) {

            Text("Confirm Booking")

        }

    }

}
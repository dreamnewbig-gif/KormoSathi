package com.kormosathi.app.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun ServiceItemScreen(
    subCategoryId: String,
    navController: NavHostController
) {

    val services = listOf(
        "Fan Installation",
        "Switch Repair",
        "Light Installation",
        "House Wiring",
        "MCB Repair",
        "Inverter Wiring"
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        items(services) { service ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        // Next: Provider List
                    },
                elevation = CardDefaults.cardElevation(4.dp)
            ) {

                Column(
                    modifier = Modifier.padding(18.dp)
                ) {

                    Text(
                        text = service,
                        style = MaterialTheme.typography.titleMedium
                    )

                }

            }

        }

    }

}
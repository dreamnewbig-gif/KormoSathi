package com.kormosathi.app.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.kormosathi.app.ui.navigation.Screen

data class ProviderCard(
    val name: String,
    val profession: String,
    val rating: Double,
    val experience: String
)

@Composable
fun ProviderListScreen(
    navController: NavHostController
) {

    val providers = listOf(

        ProviderCard(
            "Rahul Das",
            "Electrician",
            4.9,
            "8 Years"
        ),

        ProviderCard(
            "Sanjib Roy",
            "Electrician",
            4.8,
            "5 Years"
        ),

        ProviderCard(
            "Bappa Mondal",
            "Electrician",
            4.7,
            "10 Years"
        )

    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),

        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        items(providers) { provider ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {

                        navController.navigate(
                            Screen.Booking.route
                        )

                    }
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),

                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        Icons.Default.Person,
                        null,
                        modifier = Modifier.size(48.dp)
                    )

                    Spacer(Modifier.width(16.dp))

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {

                        Text(
                            provider.name,
                            fontWeight = FontWeight.Bold
                        )

                        Text(provider.profession)

                        Text("⭐ ${provider.rating}")

                        Text(provider.experience)

                    }

                }

            }

        }

    }

}
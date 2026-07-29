package com.kormosathi.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

data class BookingCardModel(
    val service: String,
    val provider: String,
    val date: String,
    val time: String,
    val status: String
)

@Composable
fun MyBookingsScreen(
    navController: NavHostController
) {

    val bookings = listOf(

        BookingCardModel(
            "AC Repair",
            "Rahul Das",
            "21 Jul 2026",
            "10:00 AM",
            "Pending"
        ),

        BookingCardModel(
            "RO Service",
            "Sanjib Roy",
            "19 Jul 2026",
            "2:30 PM",
            "Accepted"
        ),

        BookingCardModel(
            "Electrician",
            "Bappa Mondal",
            "17 Jul 2026",
            "4:00 PM",
            "Completed"
        )

    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),

        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        items(bookings) { booking ->

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        booking.service,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(Modifier.height(6.dp))

                    Text("Provider : ${booking.provider}")

                    Text("Date : ${booking.date}")

                    Text("Time : ${booking.time}")

                    Spacer(Modifier.height(10.dp))

                    AssistChip(
                        onClick = {},
                        label = {
                            Text(booking.status)
                        },
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = when (booking.status) {
                                "Pending" -> Color(0xFFFFF3CD)
                                "Accepted" -> Color(0xFFD1E7DD)
                                "Completed" -> Color(0xFFCCE5FF)
                                "Cancelled" -> Color(0xFFF8D7DA)
                                else -> MaterialTheme.colorScheme.surfaceVariant
                            }
                        )
                    )

                }

            }

        }

    }

}
package com.kormosathi.app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.kormosathi.app.model.Booking
import com.kormosathi.app.viewmodel.BookingViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun MyBookingsScreen(navController: NavHostController) {
    val BookingViewModel: BookingViewModel = viewModel()
    val uiState by BookingViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        BookingViewModel.loadMyBookings()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }

            Text(
                text = "আমার আবেদন",
                style = MaterialTheme.typography.headlineSmall
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (uiState.isLoading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (uiState.Bookings.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("কোন আবেদন পাওয়া যায়নি")
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(uiState.Bookings) { Booking ->
                    BookingCard(Booking)
                }
            }
        }
    }
}

@Composable
private fun BookingCard(Booking: Booking) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "চাকরি ID: ${Booking.ServiceId.take(8)}...",
                    style = MaterialTheme.typography.bodySmall
                )

                val statusColor = when (Booking.status) {
                    "accepted" -> Color(0xFF4CAF50)
                    "rejected" -> Color(0xFFf44336)
                    else -> Color(0xFF2196F3)
                }

                Text(
                    text = when (Booking.status) {
                        "pending" -> "অপেক্ষমাণ"
                        "accepted" -> "গ্রহণ করা হয়েছে"
                        "rejected" -> "প্রত্যাখ্যান করা হয়েছে"
                        else -> Booking.status
                    },
                    style = MaterialTheme.typography.bodySmall,
                    color = statusColor
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "আবেদন করা হয়েছে: ${formatDate(Booking.appliedAt)}",
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "ফোন: ${Booking.phone}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

private fun formatDate(timestamp: Long): String {
    val sdf = SimpleDateFormat("dd MMM yyyy", Locale("bn", "BD"))
    return sdf.format(Date(timestamp))
}

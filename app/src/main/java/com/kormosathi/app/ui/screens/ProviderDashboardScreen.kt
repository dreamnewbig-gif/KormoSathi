package com.kormosathi.app.ui.screens

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.kormosathi.app.ui.navigation.Screen

data class DashboardItem(
    val title: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)

@Composable
fun ProviderDashboardScreen(
    navController: NavHostController
) {

    val context = LocalContext.current

    BackHandler {

        navController.navigate(
            Screen.Profile.route
        ) {
            popUpTo(
                Screen.Profile.route
            ) {
                inclusive = false
            }

            launchSingleTop = true
        }

    }

    val items = listOf(

        DashboardItem(
            "My Services",
            Icons.Default.Build
        ),

        DashboardItem(
            "Bookings",
            Icons.Default.Event
        ),

        DashboardItem(
            "Today's Jobs",
            Icons.Default.Work
        ),

        DashboardItem(
            "Earnings",
            Icons.Default.AttachMoney
        ),

        DashboardItem(
            "Profile",
            Icons.Default.AccountCircle
        ),

        DashboardItem(
            "Settings",
            Icons.Default.Settings
        )

    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Provider Dashboard",
            style =
                MaterialTheme
                    .typography
                    .headlineMedium,
            fontWeight =
                FontWeight.Bold
        )

        Spacer(
            modifier =
                Modifier.height(6.dp)
        )

        Text(
            text =
                "আপনার সার্ভিস, বুকিং এবং কাজ পরিচালনা করুন",
            style =
                MaterialTheme
                    .typography
                    .bodyMedium
        )

        Spacer(
            modifier =
                Modifier.height(20.dp)
        )

        LazyVerticalGrid(
            columns =
                GridCells.Fixed(2),

            verticalArrangement =
                Arrangement.spacedBy(14.dp),

            horizontalArrangement =
                Arrangement.spacedBy(14.dp),

            modifier =
                Modifier.fillMaxSize()
        ) {

            items(
                items = items,
                key = {
                    it.title
                }
            ) { item ->

                Card(
                    modifier =
                        Modifier
                            .height(130.dp)
                            .clickable {

                                when (
                                    item.title
                                ) {

                                    "My Services" -> {

                                        Toast
                                            .makeText(
                                                context,
                                                "My Services শীঘ্রই চালু হবে",
                                                Toast.LENGTH_SHORT
                                            )
                                            .show()

                                    }

                                    "Bookings" -> {

                                        navController.navigate(
                                            Screen
                                                .MyBookings
                                                .route
                                        )

                                    }

                                    "Today's Jobs" -> {

                                        Toast
                                            .makeText(
                                                context,
                                                "আজ কোনো কাজ নেই",
                                                Toast.LENGTH_SHORT
                                            )
                                            .show()

                                    }

                                    "Earnings" -> {

                                        Toast
                                            .makeText(
                                                context,
                                                "Earnings শীঘ্রই চালু হবে",
                                                Toast.LENGTH_SHORT
                                            )
                                            .show()

                                    }

                                    "Profile" -> {

                                        navController.navigate(
                                            Screen
                                                .Profile
                                                .route
                                        )

                                    }

                                    "Settings" -> {

                                        Toast
                                            .makeText(
                                                context,
                                                "Settings শীঘ্রই চালু হবে",
                                                Toast.LENGTH_SHORT
                                            )
                                            .show()

                                    }

                                }

                            },

                    elevation =
                        CardDefaults
                            .cardElevation(
                                defaultElevation =
                                    4.dp
                            )
                ) {

                    Column(
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .padding(12.dp),

                        verticalArrangement =
                            Arrangement.Center,

                        horizontalAlignment =
                            Alignment.CenterHorizontally
                    ) {

                        Icon(
                            imageVector =
                                item.icon,

                            contentDescription =
                                item.title
                        )

                        Spacer(
                            modifier =
                                Modifier.height(12.dp)
                        )

                        Text(
                            text =
                                item.title,

                            style =
                                MaterialTheme
                                    .typography
                                    .titleMedium
                        )

                    }

                }

            }

        }

    }

}
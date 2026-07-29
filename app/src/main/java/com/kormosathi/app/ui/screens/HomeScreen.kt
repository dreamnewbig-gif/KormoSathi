package com.kormosathi.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kormosathi.app.ui.navigation.Screen
import com.kormosathi.app.viewmodel.AuthViewModel
import com.kormosathi.app.viewmodel.CategoryViewModel
import com.kormosathi.app.utils.FirestoreSeeder

@Composable
fun HomeScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {

    val categoryViewModel: CategoryViewModel = viewModel()
    val state by categoryViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        FirestoreSeeder.seedCategories()
    }

    var search by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "কর্মসাথী",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Find trusted professionals near you",
            color = Color.Gray
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = search,
            onValueChange = { search = it },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(Icons.Default.Search, null)
            },
            placeholder = {
                Text("Search services...")
            },
            shape = RoundedCornerShape(16.dp)
        )

        Spacer(Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(
                    Color(0xFF1565C0),
                    RoundedCornerShape(20.dp)
                ),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = "🔥 Special Offers",
                color = Color.White,
                style = MaterialTheme.typography.headlineSmall
            )

        }

        Spacer(Modifier.height(20.dp))

        Text(
            "Categories",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(12.dp))

        if (state.isLoading) {

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }

        } else {

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(state.categories) { category ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(
                                    Screen.SubCategory.createRoute(category.id)
                                )
                            },
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {

                        Column(
                            modifier = Modifier.padding(20.dp)
                        ) {

                            Text(
                                category.nameBn,
                                style = MaterialTheme.typography.titleMedium
                            )

                            Spacer(Modifier.height(4.dp))

                            Text(
                                category.nameEn,
                                color = Color.Gray
                            )

                        }

                    }

                }

            }

        }

        Divider()

        Spacer(Modifier.height(10.dp))

        Text(
            "Quick Access",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(10.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                "Bookings",
                modifier = Modifier.clickable {
                    navController.navigate(Screen.MyBookings.route)
                }
            )

            Text(
                "Profile",
                modifier = Modifier.clickable {
                    navController.navigate(Screen.Profile.route)
                }
            )

            Text(
                "Logout",
                modifier = Modifier.clickable {
                    authViewModel.logout()
                    navController.navigate(Screen.Welcome.route) {
                        popUpTo(0)
                    }
                }
            )

        }

    }

}
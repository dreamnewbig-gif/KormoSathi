package com.kormosathi.app.ui.screens

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.kormosathi.app.ui.navigation.Screen
import com.kormosathi.app.utils.FirestoreSeeder
import com.kormosathi.app.viewmodel.AuthViewModel
import com.kormosathi.app.viewmodel.CategoryViewModel

@Composable
fun HomeScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {

    val context = LocalContext.current

    var backPressedTime by remember {
        mutableLongStateOf(0L)
    }

    BackHandler {

        val currentTime = System.currentTimeMillis()

        if (currentTime - backPressedTime < 2000) {

            (context as? Activity)?.finish()

        } else {

            backPressedTime = currentTime

            Toast.makeText(
                context,
                "Please press again to exit",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    val categoryViewModel: CategoryViewModel = viewModel()

    val state by categoryViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        FirestoreSeeder.seedCategories()
    }

    var search by remember {
        mutableStateOf("")
    }

    val isSearching = search.trim().isNotEmpty()

    val filteredCategories = remember(
        search,
        state.categories
    ) {

        if (search.isBlank()) {

            state.categories

        } else {

            state.categories.filter { category ->

                category.nameBn.contains(
                    search.trim(),
                    ignoreCase = true
                ) ||
                        category.nameEn.contains(
                            search.trim(),
                            ignoreCase = true
                        )

            }.sortedByDescending { category ->

                category.nameEn.startsWith(
                    search.trim(),
                    ignoreCase = true
                ) ||
                        category.nameBn.startsWith(
                            search.trim(),
                            ignoreCase = true
                        )
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 16.dp
            )
    ) {

        Text(
            text = "কর্মসাথী",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "আপনার এলাকার বিশ্বস্ত কারিগর",
            color = Color.Gray
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        OutlinedTextField(
            value = search,

            onValueChange = {
                search = it
            },

            modifier = Modifier.fillMaxWidth(),

            leadingIcon = {

                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
                )
            },

            placeholder = {
                Text("সার্ভিস বা ক্যাটাগরি খুঁজুন")
            },

            singleLine = true,

            shape = RoundedCornerShape(16.dp)
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        /*
         * Search করার সময় offer banner hide থাকবে।
         * ফলে result search bar-এর ঠিক নিচে দেখা যাবে।
         */
        if (!isSearching) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(
                        color = Color(0xFF1565C0),
                        shape = RoundedCornerShape(20.dp)
                    ),

                contentAlignment = Alignment.Center
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "🔥 Special Offers",
                        color = Color.White,
                        style = MaterialTheme.typography.headlineSmall
                    )

                    Spacer(
                        modifier = Modifier.height(6.dp)
                    )

                    Text(
                        text = "আজকের বিশেষ সার্ভিস অফার দেখুন",
                        color = Color.White.copy(
                            alpha = 0.85f
                        )
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )
        }

        Text(
            text = if (isSearching) {
                "Search Results"
            } else {
                "Categories"
            },

            style = MaterialTheme.typography.titleLarge,

            fontWeight = FontWeight.Bold
        )

        if (isSearching) {

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(
                text = "\"${search.trim()}\" এর জন্য ফলাফল",
                color = Color.Gray,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        if (state.isLoading) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),

                contentAlignment = Alignment.Center
            ) {

                CircularProgressIndicator()
            }

        } else if (
            isSearching &&
            filteredCategories.isEmpty()
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),

                contentAlignment = Alignment.Center
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "কোনো সার্ভিস পাওয়া যায়নি",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(
                        modifier = Modifier.height(6.dp)
                    )

                    Text(
                        text = "অন্য কোনো নাম দিয়ে খুঁজে দেখুন",
                        color = Color.Gray,
                        textAlign = TextAlign.Center
                    )
                }
            }

        } else {

            LazyVerticalGrid(

                columns = GridCells.Fixed(2),

                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),

                verticalArrangement = Arrangement.spacedBy(12.dp),

                horizontalArrangement = Arrangement.spacedBy(12.dp),

                contentPadding = PaddingValues(
                    bottom = 16.dp
                )

            ) {

                items(
                    items = filteredCategories,
                    key = { category ->
                        category.id
                    }
                ) { category ->

                    Card(

                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {

                                navController.navigate(

                                    Screen.SubCategory.createRoute(
                                        category.id
                                    )

                                ) {

                                    launchSingleTop = true
                                }
                            },

                        shape = RoundedCornerShape(18.dp),

                        colors = CardDefaults.cardColors(
                            containerColor = Color(
                                0xFFF4F7FA
                            )
                        ),

                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 2.dp
                        )

                    ) {

                        Column(

                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = 16.dp,
                                    vertical = 18.dp
                                )

                        ) {

                            Text(

                                text = category.nameBn,

                                style = MaterialTheme
                                    .typography
                                    .titleMedium,

                                fontWeight = FontWeight.Bold
                            )

                            Spacer(
                                modifier = Modifier.height(5.dp)
                            )

                            Text(

                                text = category.nameEn,

                                color = Color(
                                    0xFF607D8B
                                ),

                                style = MaterialTheme
                                    .typography
                                    .bodyMedium
                            )

                            Spacer(
                                modifier = Modifier.height(10.dp)
                            )

                            Text(

                                text = "সার্ভিস দেখুন →",

                                color = Color(
                                    0xFF1565C0
                                ),

                                style = MaterialTheme
                                    .typography
                                    .labelMedium,

                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }
        }

        HorizontalDivider()

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        Row(

            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    bottom = 12.dp
                ),

            horizontalArrangement = Arrangement.SpaceEvenly,

            verticalAlignment = Alignment.CenterVertically

        ) {

            Text(

                text = "Bookings",

                color = Color(
                    0xFF1565C0
                ),

                fontWeight = FontWeight.Medium,

                modifier = Modifier.clickable {

                    navController.navigate(
                        Screen.MyBookings.route
                    )
                }
            )

            Text(

                text = "Profile",

                color = Color(
                    0xFF1565C0
                ),

                fontWeight = FontWeight.Medium,

                modifier = Modifier.clickable {

                    navController.navigate(
                        Screen.Profile.route
                    ) {

                        launchSingleTop = true
                    }
                }
            )

            Text(

                text = "Logout",

                color = Color(
                    0xFFD32F2F
                ),

                fontWeight = FontWeight.Medium,

                modifier = Modifier.clickable {

                    authViewModel.logout()

                    navController.navigate(
                        Screen.Welcome.route
                    ) {

                        popUpTo(0)

                        launchSingleTop = true
                    }
                }
            )
        }
    }
}
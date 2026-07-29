package com.kormosathi.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.kormosathi.app.ui.navigation.Screen
import com.kormosathi.app.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(
    navController: NavHostController
) {

    val profileViewModel: ProfileViewModel = viewModel()

    val uiState by profileViewModel
        .uiState
        .collectAsState()

    LaunchedEffect(Unit) {
        profileViewModel.loadProfile()
    }

    val profile = uiState.profile

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "আমার প্রোফাইল",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(
            modifier = Modifier.height(30.dp)
        )

        when {

            uiState.isLoading -> {

                CircularProgressIndicator()

            }

            uiState.errorMessage.isNotBlank() -> {

                Text(
                    text = uiState.errorMessage,
                    color = MaterialTheme.colorScheme.error
                )

            }

            profile != null -> {

                Text(
                    text = "নাম : ${profile.name}"
                )

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                Text(
                    text = "মোবাইল : ${profile.phone}"
                )

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                Text(
                    text = "কাজ : ${profile.category}"
                )

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                Text(
                    text = "জেলা : ${profile.district}"
                )

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                Text(
                    text = "ব্লক : ${profile.block}"
                )

            }

            else -> {

                Text(
                    text = "প্রোফাইল পাওয়া যায়নি"
                )

            }

        }

        Spacer(
            modifier = Modifier.height(30.dp)
        )

        Button(
            onClick = {

                navController.navigate(
                    Screen.ProviderRegistration.route
                )

            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {

            Text(
                text = "🛠️ Become a Provider"
            )

        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        OutlinedButton(
            onClick = {

                navController.popBackStack()

            },
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Text(
                text = "Back"
            )

        }

    }

}
package com.kormosathi.app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.kormosathi.app.repository.ProfileRepository
import com.kormosathi.app.ui.navigation.Screen
import com.kormosathi.app.viewmodel.ApplicationViewModel

@Composable
fun ApplyJobScreen(
    navController: NavHostController,
    jobId: String
) {
    val applicationViewModel: ApplicationViewModel = viewModel()
    val appUiState by applicationViewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    var applicantName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

    // Load profile info
    LaunchedEffect(Unit) {
        try {
            val profileRepository = ProfileRepository()
            val profile = profileRepository.getProfile()
            if (profile != null) {
                applicantName = profile.name
                phone = profile.phone
            }
        } catch (e: Exception) {
            // Handle error silently
        }
    }

    LaunchedEffect(appUiState.isSuccess) {
        if (appUiState.isSuccess) {
            snackbarHostState.showSnackbar("আপনি সফলভাবে চাকরির জন্য আবেদন করেছেন")
            navController.navigate(Screen.JobList.route) {
                popUpTo(Screen.ApplyJob.route) { inclusive = true }
            }
        }
    }

    LaunchedEffect(appUiState.errorMessage) {
        if (appUiState.errorMessage.isNotEmpty()) {
            snackbarHostState.showSnackbar(appUiState.errorMessage)
            applicationViewModel.clearError()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
        }

        Text(
            text = "চাকরির জন্য আবেদন করুন",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Form
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            OutlinedTextField(
                value = applicantName,
                onValueChange = { applicantName = it },
                label = { Text("নাম") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                singleLine = true,
                enabled = !appUiState.isLoading
            )

            OutlinedTextField(
                value = phone,
                onValueChange = { if (it.length <= 10 && it.all { ch -> ch.isDigit() }) phone = it },
                label = { Text("ফোন নম্বর") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                singleLine = true,
                enabled = !appUiState.isLoading
            )

            Button(
                onClick = {
                    if (validateForm(applicantName, phone)) {
                        applicationViewModel.applyForJob(
                            jobId,
                            applicantName,
                            phone
                        )
                    }
                },
                enabled = !appUiState.isLoading && applicantName.isNotBlank() && phone.length == 10,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                if (appUiState.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.padding(8.dp), strokeWidth = 2.dp)
                } else {
                    Text("আবেদন জমা করুন")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            SnackbarHost(hostState = snackbarHostState)
        }
    }
}

private fun validateForm(name: String, phone: String): Boolean {
    return name.isNotBlank() && phone.length == 10 && phone.all { it.isDigit() }
}

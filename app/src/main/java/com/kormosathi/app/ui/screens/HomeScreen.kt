package com.kormosathi.app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.kormosathi.app.repository.ProfileRepository
import com.kormosathi.app.ui.navigation.Screen
import com.kormosathi.app.viewmodel.AuthViewModel

@Composable
fun HomeScreen(navController: NavHostController, authViewModel: AuthViewModel) {
    
    LaunchedEffect(Unit) {
        val profileRepository = ProfileRepository()
        val isProfileCompleted = try {
            profileRepository.isProfileCompleted()
        } catch (e: Exception) {
            false
        }
        
        if (!isProfileCompleted) {
            navController.navigate(Screen.ProfileSetup.route) {
                popUpTo(Screen.Home.route) { inclusive = true }
            }
        }
    }
    
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("স্বাগতম কর্মসাথীতে")
        Button(onClick = {
            authViewModel.logout()
            navController.navigate("welcome") {
                popUpTo(0) { inclusive = true }
            }
        }) {
            Text("লগআউট")
        }
    }
}
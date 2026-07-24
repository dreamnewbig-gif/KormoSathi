package com.kormosathi.app.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun LoginScreen(userType: String) {
    Text(text = if (userType == "worker")
        "Worker Login"
    else
        "Customer Login")
}
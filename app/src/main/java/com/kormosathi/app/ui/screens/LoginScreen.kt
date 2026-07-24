package com.kormosathi.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen() {

    var phone by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "লগইন করুন",
            fontSize = 30.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = phone,
            onValueChange = {
                if (it.length <= 10) phone = it
            },
            label = { Text("মোবাইল নম্বর") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone
            ),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {

            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("OTP পাঠান")
        }
    }
}
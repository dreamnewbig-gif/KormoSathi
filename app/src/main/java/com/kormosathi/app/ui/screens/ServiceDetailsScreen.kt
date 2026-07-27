package com.kormosathi.app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.kormosathi.app.model.Service
import com.kormosathi.app.ui.navigation.Screen
import com.kormosathi.app.viewmodel.BookingViewModel
import com.kormosathi.app.viewmodel.ServiceViewModel

@Composable
fun ServiceDetailsScreen(
    navController: NavHostController,
    ServiceId: String
) {
    val ServiceViewModel: ServiceViewModel = viewModel()
    val BookingViewModel: BookingViewModel = viewModel()
    val ServiceUiState by ServiceViewModel.uiState.collectAsState()
    val appUiState by BookingViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        ServiceViewModel.getServiceDetails(ServiceId)
        BookingViewModel.checkIfApplied(ServiceId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Back Button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }
            Text(
                text = "চাকরি বিবরণ",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (ServiceUiState.isLoading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (ServiceUiState.selectedService != null) {
            val Service = ServiceUiState.selectedService!!
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                ServiceDetailsContent(Service, appUiState.hasApplied) {
                    navController.navigate(Screen.ApplyService.createRoute(ServiceId))
                }
            }
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("চাকরি খুঁজে পাওয়া যায়নি")
            }
        }
    }
}

@Composable
private fun ServiceDetailsContent(
    service: Service,
    hasApplied: Boolean,
    onApplyClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = service.title,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Service Info Grid
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            InfoRow("ক্যাটাগরি:", service.category)
            InfoRow("জেলা:", service.district)
            InfoRow("ব্লক:", service.block)
            InfoRow("বেতন:", "₹${service.salary}/দিন")
            InfoRow("নিয়োগকর্তা:", service.ProviderName)
            InfoRow("ফোন:", service.phone)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "বর্ণনা",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = service.description,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onApplyClick,
            enabled = !hasApplied,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text(if (hasApplied) "ইতিমধ্যে আবেদন করা হয়েছে" else "এখনই আবেদন করুন")
        }
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

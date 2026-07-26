package com.kormosathi.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.kormosathi.app.model.Job
import com.kormosathi.app.ui.navigation.Screen
import com.kormosathi.app.viewmodel.JobViewModel

@Composable
fun JobListScreen(navController: NavHostController) {
    val jobViewModel: JobViewModel = viewModel()
    val uiState by jobViewModel.uiState.collectAsState()

    var searchTitle by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("") }
    var selectedDistrict by remember { mutableStateOf("") }
    var categoryDropdownExpanded by remember { mutableStateOf(false) }
    var districtDropdownExpanded by remember { mutableStateOf(false) }

    val workCategories = listOf(
        "ইলেকট্রিশিয়ান",
        "প্লাম্বার",
        "নির্মাণ কর্মী",
        "কার্পেন্টার",
        "পেইন্টার",
        "গার্ডেনিং",
        "ক্লিনিং সার্ভিস",
        "এসি রিপেয়ার",
        "ওয়েলডিং",
        "ফিটিংস",
        "অন্যান্য"
    )

    val districts = listOf(
        "কোলকাতা",
        "হাওড়া",
        "চব্বিশ পরগনা",
        "পশ্চিম মেদিনীপুর",
        "পূর্ব মেদিনীপুর",
        "দক্ষিণ দিনাজপুর",
        "উত্তর দিনাজপুর",
        "দার্জিলিং",
        "কালিম্পং",
        "জলপাইগুড়ি",
        "কোচবিহার",
        "বীরভূম",
        "বর্ধমান",
        "হুগলি",
        "নদিয়া",
        "মুর্শিদাবাদ",
        "মালদা",
        "পুরুলিয়া",
        "ব্যানক্লিরা"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "চাকরি খুঁজুন",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Search and Filter Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF5F5F5))
                .padding(12.dp)
        ) {
            OutlinedTextField(
                value = searchTitle,
                onValueChange = { searchTitle = it },
                label = { Text("চাকরি খোঁজ করুন") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                singleLine = true
            )

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                // Category Filter
                OutlinedTextField(
                    value = selectedCategory,
                    onValueChange = {},
                    label = { Text("ক্যাটাগরি") },
                    modifier = Modifier
                        .weight(1f)
                        .padding(bottom = 8.dp),
                    readOnly = true,
                    trailingIcon = {
                        OutlinedButton(onClick = { categoryDropdownExpanded = true }) {
                            Text("▼")
                        }
                    }
                )

                DropdownMenu(
                    expanded = categoryDropdownExpanded,
                    onDismissRequest = { categoryDropdownExpanded = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    workCategories.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(item) },
                            onClick = {
                                selectedCategory = item
                                categoryDropdownExpanded = false
                            }
                        )
                    }
                }

                // District Filter
                OutlinedTextField(
                    value = selectedDistrict,
                    onValueChange = {},
                    label = { Text("জেলা") },
                    modifier = Modifier
                        .weight(1f)
                        .padding(bottom = 8.dp),
                    readOnly = true,
                    trailingIcon = {
                        OutlinedButton(onClick = { districtDropdownExpanded = true }) {
                            Text("▼")
                        }
                    }
                )

                DropdownMenu(
                    expanded = districtDropdownExpanded,
                    onDismissRequest = { districtDropdownExpanded = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    districts.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(item) },
                            onClick = {
                                selectedDistrict = item
                                districtDropdownExpanded = false
                            }
                        )
                    }
                }
            }

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(
                    onClick = {
                        jobViewModel.searchJobs(searchTitle, selectedCategory, selectedDistrict)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp)
                ) {
                    Text("খোঁজ করুন")
                }

                OutlinedButton(
                    onClick = {
                        searchTitle = ""
                        selectedCategory = ""
                        selectedDistrict = ""
                        jobViewModel.resetSearch()
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp)
                ) {
                    Text("রিসেট")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Jobs List
        if (uiState.isLoading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (uiState.jobs.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("কোন চাকরি পাওয়া যায়নি")
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(uiState.jobs) { job ->
                    JobCard(job, navController)
                }
            }
        }
    }
}

@Composable
private fun JobCard(job: Job, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate(Screen.JobDetails.createRoute(job.jobId))
            }
            .padding(0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = job.title,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "জেলা: ${job.district}",
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "ক্যাটাগরি: ${job.category}",
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "বেতন: ₹${job.salary}/দিন",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    navController.navigate(Screen.JobDetails.createRoute(job.jobId))
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("বিস্তারিত দেখুন")
            }
        }
    }
}

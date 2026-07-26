package com.kormosathi.app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
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
import com.kormosathi.app.model.UserProfile
import com.kormosathi.app.ui.navigation.Screen
import com.kormosathi.app.viewmodel.ProfileViewModel

@Composable
fun ProfileSetupScreen(navController: NavHostController) {
    val profileViewModel: ProfileViewModel = viewModel()
    val uiState by profileViewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    var name by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var district by remember { mutableStateOf("") }
    var block by remember { mutableStateOf("") }
    var village by remember { mutableStateOf("") }
    var pincode by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var experience by remember { mutableStateOf("") }
    var expectedSalary by remember { mutableStateOf("") }

    var genderDropdownExpanded by remember { mutableStateOf(false) }
    var districtDropdownExpanded by remember { mutableStateOf(false) }
    var categoryDropdownExpanded by remember { mutableStateOf(false) }
    var experienceDropdownExpanded by remember { mutableStateOf(false) }

    val genders = listOf("পুরুষ", "মহিলা", "অন্যান্য")
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
    val experiences = listOf(
        "নতুন",
        "১-২ বছর",
        "২-৫ বছর",
        "৫-১০ বছর",
        "১০+ বছর"
    )

    LaunchedEffect(uiState.isSuccess) {
        if (uiState.isSuccess) {
            snackbarHostState.showSnackbar("প্রোফাইল সফলভাবে সংরক্ষিত হয়েছে")
            navController.navigate(Screen.Home.route) {
                popUpTo(Screen.ProfileSetup.route) { inclusive = true }
            }
        }
    }

    LaunchedEffect(uiState.errorMessage) {
        if (uiState.errorMessage.isNotEmpty()) {
            snackbarHostState.showSnackbar(uiState.errorMessage)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "প্রোফাইল সেটআপ",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Full Name
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("সম্পূর্ণ নাম") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            singleLine = true
        )

        // Gender Dropdown
        OutlinedTextField(
            value = gender,
            onValueChange = {},
            label = { Text("লিঙ্গ") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            readOnly = true,
            trailingIcon = {
                OutlinedButton(onClick = { genderDropdownExpanded = true }) {
                    Text("▼")
                }
            }
        )
        DropdownMenu(
            expanded = genderDropdownExpanded,
            onDismissRequest = { genderDropdownExpanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            genders.forEach { item ->
                DropdownMenuItem(
                    text = { Text(item) },
                    onClick = {
                        gender = item
                        genderDropdownExpanded = false
                    }
                )
            }
        }

        // Age
        OutlinedTextField(
            value = age,
            onValueChange = { if (it.isEmpty() || it.all { ch -> ch.isDigit() }) age = it },
            label = { Text("বয়স") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            singleLine = true
        )

        // District Dropdown
        OutlinedTextField(
            value = district,
            onValueChange = {},
            label = { Text("জেলা") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
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
                        district = item
                        districtDropdownExpanded = false
                    }
                )
            }
        }

        // Block
        OutlinedTextField(
            value = block,
            onValueChange = { block = it },
            label = { Text("ব্লক") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            singleLine = true
        )

        // Village
        OutlinedTextField(
            value = village,
            onValueChange = { village = it },
            label = { Text("গ্রাম/শহর") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            singleLine = true
        )

        // Pincode
        OutlinedTextField(
            value = pincode,
            onValueChange = { if (it.isEmpty() || it.all { ch -> ch.isDigit() } && it.length <= 6) pincode = it },
            label = { Text("পিনকোড") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            singleLine = true
        )

        // Work Category Dropdown
        OutlinedTextField(
            value = category,
            onValueChange = {},
            label = { Text("কাজের ক্যাটাগরি") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
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
                        category = item
                        categoryDropdownExpanded = false
                    }
                )
            }
        }

        // Experience Dropdown
        OutlinedTextField(
            value = experience,
            onValueChange = {},
            label = { Text("অভিজ্ঞতা") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            readOnly = true,
            trailingIcon = {
                OutlinedButton(onClick = { experienceDropdownExpanded = true }) {
                    Text("▼")
                }
            }
        )
        DropdownMenu(
            expanded = experienceDropdownExpanded,
            onDismissRequest = { experienceDropdownExpanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            experiences.forEach { item ->
                DropdownMenuItem(
                    text = { Text(item) },
                    onClick = {
                        experience = item
                        experienceDropdownExpanded = false
                    }
                )
            }
        }

        // Expected Salary
        OutlinedTextField(
            value = expectedSalary,
            onValueChange = { if (it.isEmpty() || it.all { ch -> ch.isDigit() }) expectedSalary = it },
            label = { Text("প্রত্যাশিত বেতন (প্রতিদিন)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            singleLine = true
        )

        // Save Button
        Button(
            onClick = {
                if (validateFields(name, gender, age, district, block, village, pincode, category, experience, expectedSalary)) {
                    val profile = UserProfile(
                        name = name,
                        gender = gender,
                        age = age.toIntOrNull() ?: 0,
                        district = district,
                        block = block,
                        village = village,
                        pincode = pincode,
                        category = category,
                        experience = experience,
                        expectedSalary = expectedSalary,
                        profileCompleted = true
                    )
                    profileViewModel.saveProfile(profile)
                } else {
                    // Show validation error
                }
            },
            enabled = !uiState.isLoading,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator(modifier = Modifier.padding(8.dp), strokeWidth = 2.dp)
            } else {
                Text("সংরক্ষণ করুন")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        SnackbarHost(hostState = snackbarHostState)
    }
}

private fun validateFields(
    name: String,
    gender: String,
    age: String,
    district: String,
    block: String,
    village: String,
    pincode: String,
    category: String,
    experience: String,
    expectedSalary: String
): Boolean {
    return name.isNotBlank() &&
            gender.isNotBlank() &&
            age.isNotBlank() &&
            age.toIntOrNull() != null &&
            age.toInt() > 0 &&
            age.toInt() < 100 &&
            district.isNotBlank() &&
            block.isNotBlank() &&
            village.isNotBlank() &&
            pincode.isNotBlank() &&
            pincode.length == 6 &&
            category.isNotBlank() &&
            experience.isNotBlank() &&
            expectedSalary.isNotBlank() &&
            expectedSalary.toIntOrNull() != null
}

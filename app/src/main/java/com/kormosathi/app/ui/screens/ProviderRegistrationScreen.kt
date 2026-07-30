package com.kormosathi.app.ui.screens

import android.util.Patterns

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.unit.dp

import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.NavHostController

import com.google.firebase.auth.FirebaseAuth

import com.kormosathi.app.model.Provider
import com.kormosathi.app.ui.navigation.Screen
import com.kormosathi.app.viewmodel.ProviderViewModel


@Composable
fun ProviderRegistrationScreen(
    navController: NavHostController
) {

    val providerViewModel: ProviderViewModel = viewModel()

    val providerUiState by providerViewModel
        .uiState
        .collectAsState()


    LaunchedEffect(
        providerUiState.isSuccess
    ) {

        if (providerUiState.isSuccess) {

            navController.navigate(
                Screen.ProviderDashboard.route
            ) {

                popUpTo(
                    Screen.ProviderRegistration.route
                ) {
                    inclusive = true
                }

                launchSingleTop = true
            }

            providerViewModel.clearSuccess()
        }
    }


    var fullName by remember {
        mutableStateOf("")
    }

    var phone by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var gender by remember {
        mutableStateOf("")
    }

    var district by remember {
        mutableStateOf("")
    }

    var policeStation by remember {
        mutableStateOf("")
    }

    var postOffice by remember {
        mutableStateOf("")
    }

    var areaType by remember {
        mutableStateOf("")
    }

    var villageOrLocality by remember {
        mutableStateOf("")
    }

    var pincode by remember {
        mutableStateOf("")
    }

    var experience by remember {
        mutableStateOf("")
    }

    var aadhaarNumber by remember {
        mutableStateOf("")
    }

    var licenceNumber by remember {
        mutableStateOf("")
    }

    var about by remember {
        mutableStateOf("")
    }

    var errorMessage by remember {
        mutableStateOf("")
    }


    /*
     * Multiple selections
     */

    val selectedCategories =
        remember {
            mutableStateListOf<String>()
        }

    val selectedSubCategories =
        remember {
            mutableStateListOf<String>()
        }

    val selectedServices =
        remember {
            mutableStateListOf<String>()
        }


    val categories = listOf(

        "Home Services",

        "Repair & Installation",

        "Cleaning Services",

        "Vehicle Services",

        "Personal Services",

        "Education",

        "Events",

        "Business & Digital",

        "Health",

        "Travel",

        "Outdoor Services"

    )


    fun getSubCategories(
        category: String
    ): List<String> {

        return when (category) {

            "Home Services" -> listOf(

                "Electrician",

                "Plumber",

                "Carpenter",

                "Painter",

                "Mason",

                "Tiles Work",

                "False Ceiling"

            )

            "Repair & Installation" -> listOf(

                "AC Repair",

                "AC Installation",

                "RO Repair",

                "Computer Repair",

                "Laptop Repair",

                "CCTV Installation",

                "Appliance Repair"

            )

            "Cleaning Services" -> listOf(

                "Home Cleaning",

                "Office Cleaning",

                "Bathroom Cleaning",

                "Kitchen Cleaning",

                "Sofa Cleaning",

                "Water Tank Cleaning"

            )

            "Vehicle Services" -> listOf(

                "Bike Repair",

                "Car Repair",

                "Car Washing",

                "Bike Washing",

                "Tyre Service",

                "Battery Service"

            )

            "Personal Services" -> listOf(

                "Beauty Service",

                "Salon Service",

                "Makeup Artist",

                "Massage Service",

                "Personal Trainer"

            )

            "Education" -> listOf(

                "Home Tutor",

                "Computer Training",

                "Spoken English",

                "Music Teacher",

                "Dance Teacher"

            )

            "Events" -> listOf(

                "Photographer",

                "Videographer",

                "Event Decoration",

                "Catering",

                "DJ Service",

                "Event Planning"

            )

            "Business & Digital" -> listOf(

                "Graphic Design",

                "Web Development",

                "Digital Marketing",

                "Social Media Management",

                "Printing Service",

                "Logo Design"

            )

            "Health" -> listOf(

                "Home Nursing",

                "Physiotherapy",

                "Doctor Consultation",

                "Medical Assistance"

            )

            "Travel" -> listOf(

                "Car Rental",

                "Taxi Service",

                "Tour Guide",

                "Travel Booking"

            )

            "Outdoor Services" -> listOf(

                "Gardening",

                "Pest Control",

                "Security Service",

                "Outdoor Cleaning"

            )

            else -> emptyList()
        }
    }


    fun getServices(
        subCategory: String
    ): List<String> {

        return when (subCategory) {

            "Electrician" -> listOf(

                "Fan Installation",

                "Switch Repair",

                "Light Installation",

                "House Wiring",

                "MCB Repair",

                "Inverter Wiring"

            )

            "Plumber" -> listOf(

                "Tap Repair",

                "Pipe Repair",

                "Bathroom Fitting",

                "Water Tank Installation",

                "Drainage Repair"

            )

            "Carpenter" -> listOf(

                "Door Repair",

                "Window Repair",

                "Furniture Repair",

                "Wooden Furniture Making",

                "Modular Furniture"

            )

            "AC Repair" -> listOf(

                "AC Repair",

                "AC Service",

                "AC Gas Charging",

                "AC Installation"

            )

            "Graphic Design" -> listOf(

                "Logo Design",

                "Banner Design",

                "Visiting Card Design",

                "Social Media Post Design",

                "Flex Design",

                "Wedding Card Design"

            )

            "Photographer" -> listOf(

                "Wedding Photography",

                "Birthday Photography",

                "Event Photography",

                "Product Photography"

            )

            "Home Tutor" -> listOf(

                "Primary Tuition",

                "Secondary Tuition",

                "Higher Secondary Tuition"

            )

            else -> listOf(

                "General Service"
            )
        }
    }


    val availableSubCategories =

        selectedCategories
            .flatMap {

                getSubCategories(
                    it
                )

            }
            .distinct()


    val availableServices =

        selectedSubCategories
            .flatMap {

                getServices(
                    it
                )

            }
            .distinct()


    Column(

        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(
                rememberScrollState()
            )
            .padding(16.dp)

    ) {


        Text(

            text =
                "Provider Registration",

            style =
                MaterialTheme
                    .typography
                    .headlineMedium

        )


        Spacer(
            modifier =
                Modifier.height(16.dp)
        )


        Text(

            text =
                "Personal Details",

            style =
                MaterialTheme
                    .typography
                    .titleLarge

        )


        Spacer(
            modifier =
                Modifier.height(12.dp)
        )


        OutlinedTextField(

            value =
                fullName,

            onValueChange = {

                fullName = it

            },

            label = {

                Text(
                    "Full Name *"
                )

            },

            modifier =
                Modifier.fillMaxWidth()

        )


        Spacer(
            modifier =
                Modifier.height(12.dp)
        )


        OutlinedTextField(

            value =
                phone,

            onValueChange = {

                phone =
                    it.filter {
                            character ->

                        character.isDigit()

                    }
                        .take(10)

            },

            label = {

                Text(
                    "Phone Number *"
                )

            },

            keyboardOptions =

                KeyboardOptions(

                    keyboardType =
                        KeyboardType.Phone

                ),

            modifier =
                Modifier.fillMaxWidth()

        )


        Spacer(
            modifier =
                Modifier.height(12.dp)
        )


        OutlinedTextField(

            value =
                email,

            onValueChange = {

                email = it

            },

            label = {

                Text(
                    "Email"
                )

            },

            keyboardOptions =

                KeyboardOptions(

                    keyboardType =
                        KeyboardType.Email

                ),

            modifier =
                Modifier.fillMaxWidth()

        )


        Spacer(
            modifier =
                Modifier.height(12.dp)
        )


        OutlinedTextField(

            value =
                gender,

            onValueChange = {

                gender = it

            },

            label = {

                Text(
                    "Gender *"
                )

            },

            modifier =
                Modifier.fillMaxWidth()

        )


        Spacer(
            modifier =
                Modifier.height(24.dp)
        )


        Text(

            text =
                "Location Details",

            style =
                MaterialTheme
                    .typography
                    .titleLarge

        )


        Spacer(
            modifier =
                Modifier.height(12.dp)
        )


        OutlinedTextField(

            value =
                district,

            onValueChange = {

                district = it

            },

            label = {

                Text(
                    "District *"
                )

            },

            modifier =
                Modifier.fillMaxWidth()

        )


        Spacer(
            modifier =
                Modifier.height(12.dp)
        )


        OutlinedTextField(

            value =
                policeStation,

            onValueChange = {

                policeStation = it

            },

            label = {

                Text(
                    "Police Station / Block *"
                )

            },

            modifier =
                Modifier.fillMaxWidth()

        )


        Spacer(
            modifier =
                Modifier.height(12.dp)
        )


        OutlinedTextField(

            value =
                postOffice,

            onValueChange = {

                postOffice = it

            },

            label = {

                Text(
                    "Post Office *"
                )

            },

            modifier =
                Modifier.fillMaxWidth()

        )


        Spacer(
            modifier =
                Modifier.height(12.dp)
        )


        OutlinedTextField(

            value =
                areaType,

            onValueChange = {

                areaType = it

            },

            label = {

                Text(
                    "Area Type *"
                )

            },

            modifier =
                Modifier.fillMaxWidth()

        )


        Spacer(
            modifier =
                Modifier.height(12.dp)
        )


        OutlinedTextField(

            value =
                villageOrLocality,

            onValueChange = {

                villageOrLocality = it

            },

            label = {

                Text(
                    "Village / Locality *"
                )

            },

            modifier =
                Modifier.fillMaxWidth()

        )


        Spacer(
            modifier =
                Modifier.height(12.dp)
        )


        OutlinedTextField(

            value =
                pincode,

            onValueChange = {

                pincode =
                    it.filter {
                            character ->

                        character.isDigit()

                    }
                        .take(6)

            },

            label = {

                Text(
                    "Pincode *"
                )

            },

            keyboardOptions =

                KeyboardOptions(

                    keyboardType =
                        KeyboardType.Number

                ),

            modifier =
                Modifier.fillMaxWidth()

        )


        Spacer(
            modifier =
                Modifier.height(24.dp)
        )


        Text(

            text =
                "Professional Details",

            style =
                MaterialTheme
                    .typography
                    .titleLarge

        )


        Spacer(
            modifier =
                Modifier.height(12.dp)
        )


        OutlinedTextField(

            value =
                experience,

            onValueChange = {

                experience = it

            },

            label = {

                Text(
                    "Experience (Years) *"
                )

            },

            keyboardOptions =

                KeyboardOptions(

                    keyboardType =
                        KeyboardType.Number

                ),

            modifier =
                Modifier.fillMaxWidth()

        )


        Spacer(
            modifier =
                Modifier.height(24.dp)
        )


        Text(

            text =
                "Select Categories",

            style =
                MaterialTheme
                    .typography
                    .titleLarge

        )


        Spacer(
            modifier =
                Modifier.height(8.dp)
        )


        categories.forEach {
                category ->

            SelectionRow(

                text =
                    category,

                selected =
                    selectedCategories
                        .contains(
                            category
                        ),

                onClick = {

                    if (
                        selectedCategories
                            .contains(
                                category
                            )
                    ) {

                        selectedCategories
                            .remove(
                                category
                            )

                    } else {

                        selectedCategories
                            .add(
                                category
                            )

                    }


                    selectedSubCategories
                        .removeAll {

                            !selectedCategories
                                .flatMap {
                                        selected ->

                                    getSubCategories(
                                        selected
                                    )

                                }
                                .contains(
                                    it
                                )

                        }


                    selectedServices
                        .removeAll {

                            !selectedSubCategories
                                .flatMap {
                                        selected ->

                                    getServices(
                                        selected
                                    )

                                }
                                .contains(
                                    it
                                )

                        }

                }

            )

        }


        if (
            selectedCategories
                .isNotEmpty()
        ) {

            Spacer(
                modifier =
                    Modifier.height(24.dp)
            )


            Text(

                text =
                    "Select Sub Categories",

                style =
                    MaterialTheme
                        .typography
                        .titleLarge

            )


            Spacer(
                modifier =
                    Modifier.height(8.dp)
            )


            availableSubCategories
                .forEach {
                        subCategory ->

                    SelectionRow(

                        text =
                            subCategory,

                        selected =

                            selectedSubCategories
                                .contains(
                                    subCategory
                                ),

                        onClick = {

                            if (

                                selectedSubCategories
                                    .contains(
                                        subCategory
                                    )

                            ) {

                                selectedSubCategories
                                    .remove(
                                        subCategory
                                    )

                            } else {

                                selectedSubCategories
                                    .add(
                                        subCategory
                                    )

                            }


                            selectedServices
                                .removeAll {

                                    !selectedSubCategories
                                        .flatMap {
                                                selected ->

                                            getServices(
                                                selected
                                            )

                                        }
                                        .contains(
                                            it
                                        )

                                }

                        }

                    )

                }

        }


        if (
            selectedSubCategories
                .isNotEmpty()
        ) {

            Spacer(
                modifier =
                    Modifier.height(24.dp)
            )


            Text(

                text =
                    "Select Services",

                style =
                    MaterialTheme
                        .typography
                        .titleLarge

            )


            Spacer(
                modifier =
                    Modifier.height(8.dp)
            )


            availableServices
                .forEach {
                        service ->

                    SelectionRow(

                        text =
                            service,

                        selected =

                            selectedServices
                                .contains(
                                    service
                                ),

                        onClick = {

                            if (

                                selectedServices
                                    .contains(
                                        service
                                    )

                            ) {

                                selectedServices
                                    .remove(
                                        service
                                    )

                            } else {

                                selectedServices
                                    .add(
                                        service
                                    )

                            }

                        }

                    )

                }

        }


        Spacer(
            modifier =
                Modifier.height(24.dp)
        )


        Text(

            text =
                "Verification Details",

            style =
                MaterialTheme
                    .typography
                    .titleLarge

        )


        Spacer(
            modifier =
                Modifier.height(12.dp)
        )


        OutlinedTextField(

            value =
                aadhaarNumber,

            onValueChange = {

                aadhaarNumber =
                    it.filter {
                            character ->

                        character.isDigit()

                    }
                        .take(12)

            },

            label = {

                Text(
                    "Aadhaar Number *"
                )

            },

            keyboardOptions =

                KeyboardOptions(

                    keyboardType =
                        KeyboardType.Number

                ),

            modifier =
                Modifier.fillMaxWidth()

        )


        Spacer(
            modifier =
                Modifier.height(12.dp)
        )


        OutlinedTextField(

            value =
                licenceNumber,

            onValueChange = {

                licenceNumber = it

            },

            label = {

                Text(
                    "Licence / Registration Number"
                )

            },

            modifier =
                Modifier.fillMaxWidth()

        )


        Spacer(
            modifier =
                Modifier.height(12.dp)
        )


        OutlinedTextField(

            value =
                about,

            onValueChange = {

                about = it

            },

            label = {

                Text(
                    "About Yourself"
                )

            },

            modifier =
                Modifier.fillMaxWidth(),

            minLines =
                4

        )


        if (
            errorMessage
                .isNotBlank()
        ) {

            Spacer(
                modifier =
                    Modifier.height(12.dp)
            )


            Text(

                text =
                    errorMessage,

                color =
                    MaterialTheme
                        .colorScheme
                        .error

            )

        }


        if (
            providerUiState
                .errorMessage
                .isNotBlank()
        ) {

            Spacer(
                modifier =
                    Modifier.height(12.dp)
            )


            Text(

                text =
                    providerUiState
                        .errorMessage,

                color =
                    MaterialTheme
                        .colorScheme
                        .error

            )

        }


        Spacer(
            modifier =
                Modifier.height(24.dp)
        )


        Button(

            onClick = {

                when {

                    fullName
                        .trim()
                        .length < 3 -> {

                        errorMessage =
                            "Please enter your full name"

                    }


                    phone
                        .length != 10 -> {

                        errorMessage =
                            "Please enter a valid 10-digit phone number"

                    }


                    email
                        .isNotBlank()

                            &&

                            !Patterns
                                .EMAIL_ADDRESS
                                .matcher(
                                    email
                                )
                                .matches() -> {

                        errorMessage =
                            "Please enter a valid email address"

                    }


                    district
                        .isBlank() -> {

                        errorMessage =
                            "Please enter your district"

                    }


                    policeStation
                        .isBlank() -> {

                        errorMessage =
                            "Please enter your police station or block"

                    }


                    postOffice
                        .isBlank() -> {

                        errorMessage =
                            "Please enter your post office"

                    }


                    villageOrLocality
                        .isBlank() -> {

                        errorMessage =
                            "Please enter your village or locality"

                    }


                    pincode
                        .length != 6 -> {

                        errorMessage =
                            "Please enter a valid 6-digit pincode"

                    }


                    selectedCategories
                        .isEmpty() -> {

                        errorMessage =
                            "Please select at least one category"

                    }


                    selectedSubCategories
                        .isEmpty() -> {

                        errorMessage =
                            "Please select at least one sub category"

                    }


                    selectedServices
                        .isEmpty() -> {

                        errorMessage =
                            "Please select at least one service"

                    }


                    aadhaarNumber
                        .length != 12 -> {

                        errorMessage =
                            "Please enter a valid 12-digit Aadhaar number"

                    }


                    else -> {

                        val currentUser =

                            FirebaseAuth
                                .getInstance()
                                .currentUser


                        if (
                            currentUser == null
                        ) {

                            errorMessage =
                                "User not logged in"

                        } else {

                            val provider =

                                Provider(

                                    id =
                                        currentUser.uid,

                                    userId =
                                        currentUser.uid,

                                    fullName =
                                        fullName.trim(),

                                    phone =
                                        phone.trim(),

                                    email =
                                        email.trim(),

                                    gender =
                                        gender.trim(),

                                    district =
                                        district.trim(),

                                    policeStationOrBlock =
                                        policeStation.trim(),

                                    postOffice =
                                        postOffice.trim(),

                                    areaType =
                                        areaType.trim(),

                                    villageOrLocality =
                                        villageOrLocality
                                            .trim(),

                                    pincode =
                                        pincode,

                                    categoryIds =

                                        selectedCategories
                                            .toList(),

                                    subCategoryIds =

                                        selectedSubCategories
                                            .toList(),

                                    serviceItemIds =

                                        selectedServices
                                            .toList(),

                                    experienceYears =

                                        experience
                                            .toIntOrNull()
                                            ?: 0,

                                    about =
                                        about.trim(),

                                    identityDocumentType =
                                        "Aadhaar",

                                    identityDocumentLastFour =

                                        aadhaarNumber
                                            .takeLast(4),

                                    licenceOrRegistrationNumber =

                                        licenceNumber
                                            .trim(),

                                    verificationStatus =
                                        "Pending",

                                    profileCompleted =
                                        true,

                                    isVerified =
                                        false,

                                    isApproved =
                                        false,

                                    isAvailable =
                                        true,

                                    createdAt =

                                        System
                                            .currentTimeMillis()

                                )


                            providerViewModel
                                .saveProvider(
                                    provider
                                )

                        }

                    }

                }

            },

            enabled =

                !providerUiState
                    .isLoading,

            modifier =

                Modifier
                    .fillMaxWidth()
                    .height(54.dp)

        ) {

            if (
                providerUiState
                    .isLoading
            ) {

                CircularProgressIndicator(

                    strokeWidth =
                        2.dp

                )

            } else {

                Text(

                    text =
                        "Submit Provider Registration"

                )

            }

        }


        Spacer(
            modifier =
                Modifier.height(30.dp)
        )

    }

}


@Composable
private fun SelectionRow(

    text: String,

    selected: Boolean,

    onClick: () -> Unit

) {

    Row(

        modifier =

            Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 4.dp
                )

    ) {

        Button(

            onClick =
                onClick

        ) {

            Text(

                text =

                    if (selected) {

                        "✓"

                    } else {

                        "+"

                    }

            )

        }


        Spacer(
            modifier =
                Modifier.width(10.dp)
        )


        Text(

            text =
                text,

            modifier =
                Modifier
                    .padding(
                        top = 10.dp
                    )

        )

    }

}
package com.kormosathi.app.ui.screens
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.kormosathi.app.ui.navigation.Screen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProviderRegistrationScreen(
    navController: NavHostController
) {

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

    var locality by remember {
        mutableStateOf("")
    }

    var experience by remember {
        mutableStateOf("")
    }

    var category by remember {
        mutableStateOf("")
    }

    var subCategory by remember {
        mutableStateOf("")
    }

    var service by remember {
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

    var genderExpanded by remember {
        mutableStateOf(false)
    }

    var districtExpanded by remember {
        mutableStateOf(false)
    }

    var policeStationExpanded by remember {
        mutableStateOf(false)
    }

    var localityExpanded by remember {
        mutableStateOf(false)
    }

    var experienceExpanded by remember {
        mutableStateOf(false)
    }

    var categoryExpanded by remember {
        mutableStateOf(false)
    }

    var subCategoryExpanded by remember {
        mutableStateOf(false)
    }

    var serviceExpanded by remember {
        mutableStateOf(false)
    }

    val genders = listOf(
        "Male",
        "Female",
        "Other"
    )

    val districts = listOf(
        "Purba Medinipur",
        "Paschim Medinipur",
        "Jhargram",
        "Purulia",
        "Bankura",
        "Kolkata",
        "Howrah",
        "Hooghly",
        "Nadia",
        "Murshidabad",
        "Other"
    )

    val policeStations = listOf(
        "Select Police Station",
        "Egra",
        "Contai",
        "Ramnagar",
        "Bhupatinagar",
        "Tamluk",
        "Haldia",
        "Kharagpur",
        "Other"
    )

    val localities = listOf(
        "Village",
        "Ward / Municipality Area",
        "Town",
        "Other"
    )

    val experienceOptions = listOf(
        "Fresher",
        "1 Year",
        "2 Years",
        "3 Years",
        "4 Years",
        "5 Years",
        "6-10 Years",
        "More than 10 Years"
    )

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

    val subCategories = when (category) {

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

    val services = when (subCategory) {

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

        "Bike Repair" -> listOf(
            "Bike General Service",
            "Bike Engine Repair",
            "Bike Brake Repair",
            "Bike Electrical Repair"
        )

        "Car Repair" -> listOf(
            "Car General Service",
            "Car Engine Repair",
            "Car Brake Repair",
            "Car Electrical Repair"
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(
                rememberScrollState()
            )
            .padding(16.dp)
    ) {

        Text(
            text = "Provider Registration",
            style =
                MaterialTheme
                    .typography
                    .headlineMedium
        )

        Spacer(
            modifier =
                Modifier.height(8.dp)
        )

        Text(
            text =
                "প্রোভাইডার হিসেবে রেজিস্ট্রেশন করতে নিচের তথ্যগুলো পূরণ করুন"
        )

        Spacer(
            modifier =
                Modifier.height(20.dp)
        )

        Text(
            text = "Personal Details",
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
            value = fullName,
            onValueChange = {

                fullName = it

                errorMessage = ""

            },
            label = {
                Text("Full Name *")
            },
            modifier =
                Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(
            modifier =
                Modifier.height(12.dp)
        )

        OutlinedTextField(
            value = phone,
            onValueChange = {

                phone =
                    it.filter {
                            character ->
                        character.isDigit()
                    }
                        .take(10)

                errorMessage = ""

            },
            label = {
                Text("Phone Number *")
            },
            keyboardOptions =
                KeyboardOptions(
                    keyboardType =
                        KeyboardType.Phone
                ),
            modifier =
                Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(
            modifier =
                Modifier.height(12.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = {

                email = it

                errorMessage = ""

            },
            label = {
                Text("Email")
            },
            keyboardOptions =
                KeyboardOptions(
                    keyboardType =
                        KeyboardType.Email
                ),
            modifier =
                Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(
            modifier =
                Modifier.height(12.dp)
        )

        ExposedDropdownMenuBox(
            expanded =
                genderExpanded,

            onExpandedChange = {

                genderExpanded =
                    !genderExpanded

            }
        ) {

            OutlinedTextField(
                value = gender,
                onValueChange = {},
                readOnly = true,
                label = {
                    Text("Gender *")
                },
                trailingIcon = {

                    ExposedDropdownMenuDefaults
                        .TrailingIcon(
                            expanded =
                                genderExpanded
                        )

                },
                modifier =
                    Modifier
                        .menuAnchor()
                        .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded =
                    genderExpanded,

                onDismissRequest = {

                    genderExpanded =
                        false

                }
            ) {

                genders.forEach {
                        item ->

                    DropdownMenuItem(
                        text = {
                            Text(item)
                        },
                        onClick = {

                            gender = item

                            genderExpanded =
                                false

                        }
                    )

                }

            }

        }

        Spacer(
            modifier =
                Modifier.height(24.dp)
        )

        Text(
            text = "Location Details",
            style =
                MaterialTheme
                    .typography
                    .titleLarge
        )

        Spacer(
            modifier =
                Modifier.height(12.dp)
        )

        ExposedDropdownMenuBox(
            expanded =
                districtExpanded,

            onExpandedChange = {

                districtExpanded =
                    !districtExpanded

            }
        ) {

            OutlinedTextField(
                value = district,
                onValueChange = {},
                readOnly = true,
                label = {
                    Text("District *")
                },
                trailingIcon = {

                    ExposedDropdownMenuDefaults
                        .TrailingIcon(
                            expanded =
                                districtExpanded
                        )

                },
                modifier =
                    Modifier
                        .menuAnchor()
                        .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded =
                    districtExpanded,

                onDismissRequest = {

                    districtExpanded =
                        false

                }
            ) {

                districts.forEach {
                        item ->

                    DropdownMenuItem(
                        text = {
                            Text(item)
                        },
                        onClick = {

                            district =
                                item

                            policeStation =
                                ""

                            districtExpanded =
                                false

                        }
                    )

                }

            }

        }

        Spacer(
            modifier =
                Modifier.height(12.dp)
        )

        ExposedDropdownMenuBox(
            expanded =
                policeStationExpanded,

            onExpandedChange = {

                policeStationExpanded =
                    !policeStationExpanded

            }
        ) {

            OutlinedTextField(
                value = policeStation,
                onValueChange = {},
                readOnly = true,
                label = {
                    Text("Police Station / Thana *")
                },
                trailingIcon = {

                    ExposedDropdownMenuDefaults
                        .TrailingIcon(
                            expanded =
                                policeStationExpanded
                        )

                },
                modifier =
                    Modifier
                        .menuAnchor()
                        .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded =
                    policeStationExpanded,

                onDismissRequest = {

                    policeStationExpanded =
                        false

                }
            ) {

                policeStations.forEach {
                        item ->

                    DropdownMenuItem(
                        text = {
                            Text(item)
                        },
                        onClick = {

                            policeStation =
                                item

                            policeStationExpanded =
                                false

                        }
                    )

                }

            }

        }

        Spacer(
            modifier =
                Modifier.height(12.dp)
        )

        ExposedDropdownMenuBox(
            expanded =
                localityExpanded,

            onExpandedChange = {

                localityExpanded =
                    !localityExpanded

            }
        ) {

            OutlinedTextField(
                value = locality,
                onValueChange = {},
                readOnly = true,
                label = {
                    Text("Area Type *")
                },
                trailingIcon = {

                    ExposedDropdownMenuDefaults
                        .TrailingIcon(
                            expanded =
                                localityExpanded
                        )

                },
                modifier =
                    Modifier
                        .menuAnchor()
                        .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded =
                    localityExpanded,

                onDismissRequest = {

                    localityExpanded =
                        false

                }
            ) {

                localities.forEach {
                        item ->

                    DropdownMenuItem(
                        text = {
                            Text(item)
                        },
                        onClick = {

                            locality =
                                item

                            localityExpanded =
                                false

                        }
                    )

                }

            }

        }

        Spacer(
            modifier =
                Modifier.height(24.dp)
        )

        Text(
            text = "Professional Details",
            style =
                MaterialTheme
                    .typography
                    .titleLarge
        )

        Spacer(
            modifier =
                Modifier.height(12.dp)
        )

        ExposedDropdownMenuBox(
            expanded =
                experienceExpanded,

            onExpandedChange = {

                experienceExpanded =
                    !experienceExpanded

            }
        ) {

            OutlinedTextField(
                value = experience,
                onValueChange = {},
                readOnly = true,
                label = {
                    Text("Experience *")
                },
                trailingIcon = {

                    ExposedDropdownMenuDefaults
                        .TrailingIcon(
                            expanded =
                                experienceExpanded
                        )

                },
                modifier =
                    Modifier
                        .menuAnchor()
                        .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded =
                    experienceExpanded,

                onDismissRequest = {

                    experienceExpanded =
                        false

                }
            ) {

                experienceOptions.forEach {
                        item ->

                    DropdownMenuItem(
                        text = {
                            Text(item)
                        },
                        onClick = {

                            experience =
                                item

                            experienceExpanded =
                                false

                        }
                    )

                }

            }

        }

        Spacer(
            modifier =
                Modifier.height(24.dp)
        )

        Text(
            text = "Service Details",
            style =
                MaterialTheme
                    .typography
                    .titleLarge
        )

        Spacer(
            modifier =
                Modifier.height(12.dp)
        )

        ExposedDropdownMenuBox(
            expanded =
                categoryExpanded,

            onExpandedChange = {

                categoryExpanded =
                    !categoryExpanded

            }
        ) {

            OutlinedTextField(
                value = category,
                onValueChange = {},
                readOnly = true,
                label = {
                    Text("Category *")
                },
                trailingIcon = {

                    ExposedDropdownMenuDefaults
                        .TrailingIcon(
                            expanded =
                                categoryExpanded
                        )

                },
                modifier =
                    Modifier
                        .menuAnchor()
                        .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded =
                    categoryExpanded,

                onDismissRequest = {

                    categoryExpanded =
                        false

                }
            ) {

                categories.forEach {
                        item ->

                    DropdownMenuItem(
                        text = {
                            Text(item)
                        },
                        onClick = {

                            category =
                                item

                            subCategory =
                                ""

                            service =
                                ""

                            categoryExpanded =
                                false

                        }
                    )

                }

            }

        }

        Spacer(
            modifier =
                Modifier.height(12.dp)
        )

        ExposedDropdownMenuBox(
            expanded =
                subCategoryExpanded,

            onExpandedChange = {

                if (
                    category
                        .isNotBlank()
                ) {

                    subCategoryExpanded =
                        !subCategoryExpanded

                }

            }
        ) {

            OutlinedTextField(
                value = subCategory,
                onValueChange = {},
                readOnly = true,
                enabled =
                    category
                        .isNotBlank(),
                label = {
                    Text("Sub Category *")
                },
                trailingIcon = {

                    ExposedDropdownMenuDefaults
                        .TrailingIcon(
                            expanded =
                                subCategoryExpanded
                        )

                },
                modifier =
                    Modifier
                        .menuAnchor()
                        .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded =
                    subCategoryExpanded,

                onDismissRequest = {

                    subCategoryExpanded =
                        false

                }
            ) {

                subCategories.forEach {
                        item ->

                    DropdownMenuItem(
                        text = {
                            Text(item)
                        },
                        onClick = {

                            subCategory =
                                item

                            service =
                                ""

                            subCategoryExpanded =
                                false

                        }
                    )

                }

            }

        }

        Spacer(
            modifier =
                Modifier.height(12.dp)
        )

        ExposedDropdownMenuBox(
            expanded =
                serviceExpanded,

            onExpandedChange = {

                if (
                    subCategory
                        .isNotBlank()
                ) {

                    serviceExpanded =
                        !serviceExpanded

                }

            }
        ) {

            OutlinedTextField(
                value = service,
                onValueChange = {},
                readOnly = true,
                enabled =
                    subCategory
                        .isNotBlank(),
                label = {
                    Text("Service *")
                },
                trailingIcon = {

                    ExposedDropdownMenuDefaults
                        .TrailingIcon(
                            expanded =
                                serviceExpanded
                        )

                },
                modifier =
                    Modifier
                        .menuAnchor()
                        .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded =
                    serviceExpanded,

                onDismissRequest = {

                    serviceExpanded =
                        false

                }
            ) {

                services.forEach {
                        item ->

                    DropdownMenuItem(
                        text = {
                            Text(item)
                        },
                        onClick = {

                            service =
                                item

                            serviceExpanded =
                                false

                        }
                    )

                }

            }

        }

        Spacer(
            modifier =
                Modifier.height(24.dp)
        )

        Text(
            text = "Basic Verification",
            style =
                MaterialTheme
                    .typography
                    .titleLarge
        )

        Spacer(
            modifier =
                Modifier.height(8.dp)
        )

        Text(
            text =
                "তথ্য পরে Offline Verification-এর মাধ্যমে যাচাই করা হবে"
        )

        Spacer(
            modifier =
                Modifier.height(12.dp)
        )

        OutlinedTextField(
            value = aadhaarNumber,
            onValueChange = {

                aadhaarNumber =
                    it.filter {
                            character ->
                        character.isDigit()
                    }
                        .take(12)

                errorMessage = ""

            },
            label = {
                Text("Aadhaar Number *")
            },
            keyboardOptions =
                KeyboardOptions(
                    keyboardType =
                        KeyboardType.Number
                ),
            modifier =
                Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(
            modifier =
                Modifier.height(12.dp)
        )

        OutlinedTextField(
            value = licenceNumber,
            onValueChange = {

                licenceNumber =
                    it

            },
            label = {
                Text(
                    "Licence / Registration Number (If Required)"
                )
            },
            modifier =
                Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(
            modifier =
                Modifier.height(12.dp)
        )

        OutlinedTextField(
            value = about,
            onValueChange = {

                about = it

            },
            label = {
                Text("About Yourself")
            },
            modifier =
                Modifier.fillMaxWidth(),
            minLines = 4
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

                    phone.length != 10 -> {

                        errorMessage =
                            "Please enter a valid 10-digit phone number"

                    }

                    email
                        .isNotBlank()
                            &&
                            !android.util.Patterns
                                .EMAIL_ADDRESS
                                .matcher(
                                    email
                                )
                                .matches() -> {

                        errorMessage =
                            "Please enter a valid email address"

                    }

                    gender
                        .isBlank() -> {

                        errorMessage =
                            "Please select your gender"

                    }

                    district
                        .isBlank() -> {

                        errorMessage =
                            "Please select your district"

                    }

                    policeStation
                        .isBlank() -> {

                        errorMessage =
                            "Please select your police station"

                    }

                    locality
                        .isBlank() -> {

                        errorMessage =
                            "Please select your area type"

                    }

                    experience
                        .isBlank() -> {

                        errorMessage =
                            "Please select your experience"

                    }

                    category
                        .isBlank() -> {

                        errorMessage =
                            "Please select a category"

                    }

                    subCategory
                        .isBlank() -> {

                        errorMessage =
                            "Please select a sub category"

                    }

                    service
                        .isBlank() -> {

                        errorMessage =
                            "Please select a service"

                    }

                    aadhaarNumber
                        .length != 12 -> {

                        errorMessage =
                            "Please enter a valid 12-digit Aadhaar number"

                    }

                    else -> {

                        navController.navigate(
                            Screen
                                .ProviderDashboard
                                .route
                        ) {

                            popUpTo(
                                Screen
                                    .ProviderRegistration
                                    .route
                            ) {

                                inclusive =
                                    true

                            }

                        }

                    }

                }

            },
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(54.dp)
        ) {

            Text(
                text =
                    "Submit Provider Registration"
            )

        }

        Spacer(
            modifier =
                Modifier.height(30.dp)
        )

    }

}
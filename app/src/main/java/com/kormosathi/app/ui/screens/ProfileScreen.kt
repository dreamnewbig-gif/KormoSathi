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
import com.kormosathi.app.viewmodel.ProviderViewModel

@Composable
fun ProfileScreen(
    navController: NavHostController
) {

    val profileViewModel:
            ProfileViewModel = viewModel()

    val providerViewModel:
            ProviderViewModel = viewModel()


    val profileUiState by
    profileViewModel
        .uiState
        .collectAsState()


    val providerUiState by
    providerViewModel
        .uiState
        .collectAsState()


    LaunchedEffect(Unit) {

        profileViewModel
            .loadProfile()

        providerViewModel
            .loadCurrentProvider()
    }


    val profile =
        profileUiState.profile


    Column(

        modifier =
            Modifier
                .fillMaxSize()
                .padding(20.dp),

        horizontalAlignment =
            Alignment.CenterHorizontally

    ) {


        Text(

            text =
                "আমার প্রোফাইল",

            style =
                MaterialTheme
                    .typography
                    .headlineMedium

        )


        Spacer(

            modifier =
                Modifier.height(30.dp)

        )


        when {

            profileUiState.isLoading -> {

                CircularProgressIndicator()

            }


            profileUiState
                .errorMessage
                .isNotBlank() -> {

                Text(

                    text =
                        profileUiState
                            .errorMessage,

                    color =
                        MaterialTheme
                            .colorScheme
                            .error

                )

            }


            profile != null -> {

                Text(

                    text =
                        "নাম : ${profile.name}"

                )


                Spacer(

                    modifier =
                        Modifier.height(10.dp)

                )


                Text(

                    text =
                        "মোবাইল : ${profile.phone}"

                )


                Spacer(

                    modifier =
                        Modifier.height(10.dp)

                )


                Text(

                    text =
                        "কাজ : ${profile.category}"

                )


                Spacer(

                    modifier =
                        Modifier.height(10.dp)

                )


                Text(

                    text =
                        "জেলা : ${profile.district}"

                )


                Spacer(

                    modifier =
                        Modifier.height(10.dp)

                )


                Text(

                    text =
                        "ব্লক : ${profile.block}"

                )

            }


            else -> {

                Text(

                    text =
                        "প্রোফাইল পাওয়া যায়নি"

                )

            }

        }


        Spacer(

            modifier =
                Modifier.height(30.dp)

        )


        /*
         * Provider check শেষ না হওয়া পর্যন্ত
         * কোনো provider button দেখানো হবে না।
         */

        if (
            !providerUiState
                .providerChecked
        ) {

            CircularProgressIndicator(

                modifier =
                    Modifier.size(
                        30.dp
                    ),

                strokeWidth =
                    3.dp

            )

        } else {

            val provider =
                providerUiState
                    .provider


            if (
                provider == null
            ) {

                /*
                 * Provider registration নেই
                 */

                Button(

                    onClick = {

                        navController
                            .navigate(

                                Screen
                                    .ProviderRegistration
                                    .route

                            )

                    },

                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(52.dp)

                ) {

                    Text(

                        text =
                            "🛠️ Become a Provider"

                    )

                }

            } else {

                /*
                 * Provider registration আছে
                 */

                val status =
                    provider
                        .verificationStatus
                        .trim()


                when {

                    status.equals(
                        "Pending",
                        ignoreCase = true
                    ) -> {

                        Card(

                            modifier =
                                Modifier
                                    .fillMaxWidth(),

                            colors =
                                CardDefaults
                                    .cardColors(

                                        containerColor =

                                            MaterialTheme
                                                .colorScheme
                                                .secondaryContainer

                                    )

                        ) {

                            Column(

                                modifier =
                                    Modifier
                                        .padding(
                                            16.dp
                                        )

                            ) {

                                Text(

                                    text =
                                        "⏳ Provider Registration Pending",

                                    style =
                                        MaterialTheme
                                            .typography
                                            .titleMedium

                                )


                                Spacer(

                                    modifier =
                                        Modifier.height(
                                            6.dp
                                        )

                                )


                                Text(

                                    text =
                                        "আপনার তথ্য যাচাই করা হচ্ছে। অনুমোদনের পরে Provider Dashboard চালু হবে।"

                                )

                            }

                        }

                    }


                    provider
                        .isApproved -> {

                        Button(

                            onClick = {

                                navController
                                    .navigate(

                                        Screen
                                            .ProviderDashboard
                                            .route

                                    ) {

                                        launchSingleTop =
                                            true

                                    }

                            },

                            modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .height(52.dp)

                        ) {

                            Text(

                                text =
                                    "📊 Provider Dashboard"

                            )

                        }

                    }


                    else -> {

                        Card(

                            modifier =
                                Modifier
                                    .fillMaxWidth(),

                            colors =
                                CardDefaults
                                    .cardColors(

                                        containerColor =

                                            MaterialTheme
                                                .colorScheme
                                                .secondaryContainer

                                    )

                        ) {

                            Column(

                                modifier =
                                    Modifier
                                        .padding(
                                            16.dp
                                        )

                            ) {

                                Text(

                                    text =
                                        "Provider Registration Submitted",

                                    style =
                                        MaterialTheme
                                            .typography
                                            .titleMedium

                                )


                                Spacer(

                                    modifier =
                                        Modifier.height(
                                            6.dp
                                        )

                                )


                                Text(

                                    text =
                                        "আপনার Provider profile এখনো অনুমোদিত হয়নি।"

                                )

                            }

                        }

                    }

                }

            }

        }


        Spacer(

            modifier =
                Modifier.height(20.dp)

        )


        OutlinedButton(

            onClick = {

                navController
                    .popBackStack()

            },

            modifier =
                Modifier
                    .fillMaxWidth()

        ) {

            Text(

                text =
                    "Back"

            )

        }

    }

}
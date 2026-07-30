package com.kormosathi.app.ui.screens

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
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.kormosathi.app.ui.navigation.Screen
import com.kormosathi.app.utils.CategoryData

@Composable
fun ServiceItemSelectionScreen(
    navController: NavHostController,
    subCategory: String
) {

    val services =
        CategoryData.getServices(
            subCategory
        )

    var selectedService by remember(
        subCategory
    ) {
        mutableStateOf("")
    }

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp)
    ) {

        Text(
            text =
                "সার্ভিস নির্বাচন করুন",

            style =
                MaterialTheme
                    .typography
                    .headlineMedium
        )

        Spacer(
            modifier =
                Modifier.height(
                    8.dp
                )
        )

        Text(
            text =
                "নির্বাচিত সাব-ক্যাটাগরি: $subCategory",

            style =
                MaterialTheme
                    .typography
                    .bodyMedium
        )

        Spacer(
            modifier =
                Modifier.height(
                    16.dp
                )
        )

        if (
            services.isEmpty()
        ) {

            Text(
                text =
                    "এই সাব-ক্যাটাগরিতে এখন কোনো সার্ভিস নেই"
            )

        } else {

            LazyColumn(
                modifier =
                    Modifier.weight(
                        1f
                    )
            ) {

                items(
                    items =
                        services,

                    key = {
                            service ->
                        service
                    }

                ) { service ->

                    Row(

                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .clickable {

                                    selectedService =
                                        service

                                }
                                .padding(
                                    vertical =
                                        12.dp
                                ),

                        verticalAlignment =
                            Alignment
                                .CenterVertically,

                        horizontalArrangement =
                            Arrangement
                                .SpaceBetween

                    ) {

                        Text(

                            text =
                                service,

                            style =
                                MaterialTheme
                                    .typography
                                    .titleMedium

                        )

                        RadioButton(

                            selected =
                                selectedService ==
                                        service,

                            onClick = {

                                selectedService =
                                    service

                            }

                        )

                    }

                    HorizontalDivider()

                }

            }

        }

        Button(

            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(
                        52.dp
                    ),

            enabled =
                selectedService
                    .isNotBlank(),

            onClick = {

                navController.navigate(

                    Screen
                        .ProviderDashboard
                        .route

                ) {

                    launchSingleTop =
                        true

                }

            }

        ) {

            Text(
                text =
                    "Finish"
            )

        }

    }

}
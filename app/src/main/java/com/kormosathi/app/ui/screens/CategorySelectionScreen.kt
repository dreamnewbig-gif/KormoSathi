package com.kormosathi.app.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
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
fun CategorySelectionScreen(
    navController: NavHostController
) {

    var selectedCategoryId by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "ক্যাটাগরি নির্বাচন করুন",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "আপনি যে ধরনের সার্ভিস দিতে চান সেটি নির্বাচন করুন",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(
                bottom = 12.dp
            )
        ) {

            items(
                items = CategoryData.categories,
                key = { category ->
                    category.id
                }
            ) { category ->

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {

                            selectedCategoryId =
                                category.id

                        }
                        .padding(
                            vertical = 14.dp
                        ),

                    verticalAlignment =
                        Alignment.CenterVertically,

                    horizontalArrangement =
                        Arrangement.SpaceBetween
                ) {

                    Column(
                        modifier = Modifier
                            .weight(1f)
                    ) {

                        Text(
                            text = category.nameBn,
                            style =
                                MaterialTheme
                                    .typography
                                    .titleMedium
                        )

                        Spacer(
                            modifier =
                                Modifier.height(3.dp)
                        )

                        Text(
                            text = category.nameEn,
                            style =
                                MaterialTheme
                                    .typography
                                    .bodySmall
                        )

                    }

                    Checkbox(
                        checked =
                            selectedCategoryId ==
                                    category.id,

                        onCheckedChange = {

                            selectedCategoryId =
                                if (it) {
                                    category.id
                                } else {
                                    ""
                                }

                        }
                    )

                }

                HorizontalDivider()

            }

        }

        Button(
            onClick = {

                if (
                    selectedCategoryId
                        .isNotBlank()
                ) {

                    navController.navigate(

                        Screen
                            .SubCategorySelection
                            .createRoute(
                                selectedCategoryId
                            )

                    )

                }

            },

            enabled =
                selectedCategoryId
                    .isNotBlank(),

            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)

        ) {

            Text(
                text = "Continue"
            )

        }

    }

}
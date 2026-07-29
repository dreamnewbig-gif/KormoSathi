package com.kormosathi.app.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.kormosathi.app.utils.CategoryData

@Composable
fun CategorySelectionScreen(
    navController: NavHostController
) {

    val selected = remember { mutableStateListOf<String>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            "Select Categories",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {

            items(CategoryData.categories) { category ->

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {

                            if (selected.contains(category.id))
                                selected.remove(category.id)
                            else
                                selected.add(category.id)

                        }
                        .padding(vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Column {

                        Text(category.nameBn)

                        Text(
                            category.nameEn,
                            style = MaterialTheme.typography.bodySmall
                        )

                    }

                    Checkbox(
                        checked = selected.contains(category.id),
                        onCheckedChange = {

                            if (selected.contains(category.id))
                                selected.remove(category.id)
                            else
                                selected.add(category.id)

                        }
                    )

                }

                HorizontalDivider()

            }

        }

        Button(
            onClick = {

                // Next:
                // SubCategory Selection

            },
            modifier = Modifier.fillMaxWidth()
        ) {

            Text("Continue")

        }

    }

}
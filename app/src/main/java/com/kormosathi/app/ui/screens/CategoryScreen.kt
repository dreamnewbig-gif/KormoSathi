package com.kormosathi.app.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kormosathi.app.viewmodel.CategoryViewModel

@Composable
fun CategoryScreen(
    onCategoryClick: (String) -> Unit
) {

    val viewModel: CategoryViewModel = viewModel()

    val state by viewModel.uiState.collectAsState()

    when {

        // Loading
        state.isLoading -> {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {

                CircularProgressIndicator()

            }

        }

        // Error
        state.error != null -> {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = state.error ?: "Category load করা যায়নি",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )

                Button(
                    modifier = Modifier.padding(top = 16.dp),
                    onClick = {
                        viewModel.loadCategories()
                    }
                ) {

                    Text("আবার চেষ্টা করুন")

                }

            }

        }

        // Empty category list
        state.categories.isEmpty() -> {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "কোনো Category পাওয়া যায়নি",
                    style = MaterialTheme.typography.bodyLarge
                )

            }

        }

        // Category grid
        else -> {

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(12.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                items(
                    items = state.categories,
                    key = { category ->
                        category.id
                    }
                ) { category ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {

                                onCategoryClick(
                                    category.id
                                )

                            }
                    ) {

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            horizontalAlignment =
                                Alignment.CenterHorizontally
                        ) {

                            Text(
                                text = category.nameBn,
                                style = MaterialTheme
                                    .typography
                                    .titleMedium,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )

                            if (
                                category.nameEn.isNotBlank()
                            ) {

                                Text(
                                    modifier = Modifier
                                        .padding(top = 6.dp),
                                    text = category.nameEn,
                                    style = MaterialTheme
                                        .typography
                                        .bodyMedium,
                                    textAlign =
                                        TextAlign.Center
                                )

                            }

                            Spacer(
                                modifier = Modifier
                                    .height(4.dp)
                            )

                        }

                    }

                }

            }

        }

    }

}
package com.kormosathi.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.kormosathi.app.viewmodel.ProviderViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditServiceScreen(
    navController: NavHostController,
    ServiceId: String
) {

    val providerViewModel: ProviderViewModel = viewModel()

    val uiState by providerViewModel.uiState.collectAsState()


    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var district by remember { mutableStateOf("") }
    var block by remember { mutableStateOf("") }
    var salary by remember { mutableStateOf("") }



    Scaffold(

        topBar = {

            TopAppBar(

                title = {
                    Text("Edit Service")
                },

                navigationIcon = {

                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ){

                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )

                    }

                }

            )

        }

    ){ padding ->


        LazyColumn(

            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),

            verticalArrangement = Arrangement.spacedBy(12.dp)

        ){


            item {

                OutlinedTextField(

                    value = title,

                    onValueChange = {
                        title = it
                    },

                    label = {
                        Text("Service Title")
                    },

                    modifier = Modifier.fillMaxWidth()

                )

            }



            item {

                OutlinedTextField(

                    value = description,

                    onValueChange = {
                        description = it
                    },

                    label = {
                        Text("Description")
                    },

                    modifier = Modifier.fillMaxWidth()

                )

            }



            item {

                OutlinedTextField(

                    value = category,

                    onValueChange = {
                        category = it
                    },

                    label = {
                        Text("Category")
                    },

                    modifier = Modifier.fillMaxWidth()

                )

            }



            item {

                OutlinedTextField(

                    value = district,

                    onValueChange = {
                        district = it
                    },

                    label = {
                        Text("District")
                    },

                    modifier = Modifier.fillMaxWidth()

                )

            }



            item {

                OutlinedTextField(

                    value = block,

                    onValueChange = {
                        block = it
                    },

                    label = {
                        Text("Block")
                    },

                    modifier = Modifier.fillMaxWidth()

                )

            }



            item {

                OutlinedTextField(

                    value = salary,

                    onValueChange = {
                        salary = it
                    },

                    label = {
                        Text("Salary")
                    },

                    modifier = Modifier.fillMaxWidth()

                )

            }



            item {

                Button(

                    onClick = {


                        providerViewModel.updateService(

                            ServiceId = ServiceId,

                            title = title,

                            description = description,

                            category = category,

                            district = district,

                            block = block,

                            salary = salary.toDoubleOrNull()
                                ?: 0.0

                        )


                    },

                    modifier = Modifier.fillMaxWidth()

                ){

                    Text("Update")

                }


            }


        }


    }


}
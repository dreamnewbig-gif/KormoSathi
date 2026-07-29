package com.kormosathi.app.ui.screens

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.compose.runtime.mutableLongStateOf
import com.kormosathi.app.ui.navigation.Screen
import com.kormosathi.app.utils.FirestoreSeeder
import com.kormosathi.app.viewmodel.AuthViewModel
import com.kormosathi.app.viewmodel.CategoryViewModel


@Composable
fun HomeScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {


    val context = LocalContext.current


    var backPressedTime by remember {
        mutableLongStateOf(0L)
    }


    BackHandler {


        val currentTime = System.currentTimeMillis()


        if (currentTime - backPressedTime < 2000) {


            (context as? android.app.Activity)?.finish()


        } else {


            backPressedTime = currentTime


            Toast.makeText(
                context,
                "Please press again to exit",
                Toast.LENGTH_SHORT
            ).show()


        }


    }





    val categoryViewModel: CategoryViewModel = viewModel()

    val state by categoryViewModel.uiState.collectAsState()



    LaunchedEffect(Unit) {

        FirestoreSeeder.seedCategories()

    }



    var search by remember {

        mutableStateOf("")

    }



    val filteredCategories =
        state.categories.filter {


            it.nameBn.contains(search, ignoreCase = true) ||
                    it.nameEn.contains(search, ignoreCase = true)


        }




    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)

    ) {



        Text(

            text = "কর্মসাথী",

            style = MaterialTheme.typography.headlineMedium,

            fontWeight = FontWeight.Bold

        )



        Text(

            text = "আপনার এলাকার বিশ্বস্ত কারিগর",

            color = Color.Gray

        )



        Spacer(
            modifier = Modifier.height(16.dp)
        )



        OutlinedTextField(

            value = search,

            onValueChange = {

                search = it

            },


            modifier = Modifier.fillMaxWidth(),


            leadingIcon = {


                Icon(
                    Icons.Default.Search,
                    contentDescription = null
                )


            },


            placeholder = {

                Text("সার্ভিস খুঁজুন")

            },


            shape = RoundedCornerShape(16.dp)


        )




        Spacer(
            modifier = Modifier.height(20.dp)
        )




        Box(

            modifier = Modifier

                .fillMaxWidth()

                .height(150.dp)

                .background(

                    Color(0xFF1565C0),

                    RoundedCornerShape(20.dp)

                ),


            contentAlignment = Alignment.Center


        ) {


            Text(

                text = "🔥 Special Offers",

                color = Color.White,

                style = MaterialTheme.typography.headlineSmall


            )


        }





        Spacer(
            modifier = Modifier.height(20.dp)
        )





        Text(

            text = "Categories",

            style = MaterialTheme.typography.titleLarge,

            fontWeight = FontWeight.Bold


        )




        Spacer(
            modifier = Modifier.height(12.dp)
        )





        if (state.isLoading) {



            Box(

                modifier = Modifier.fillMaxWidth(),

                contentAlignment = Alignment.Center

            ) {


                CircularProgressIndicator()


            }



        } else {



            LazyVerticalGrid(

                columns = GridCells.Fixed(2),

                modifier = Modifier.weight(1f),


                verticalArrangement = Arrangement.spacedBy(12.dp),

                horizontalArrangement = Arrangement.spacedBy(12.dp)


            ) {



                items(filteredCategories) { category ->



                    Card(


                        modifier = Modifier

                            .fillMaxWidth()

                            .clickable {


                                navController.navigate(

                                    Screen.SubCategory.createRoute(
                                        category.id
                                    )

                                ) {


                                    launchSingleTop = true

                                }


                            },


                        elevation = CardDefaults.cardElevation(4.dp)


                    ) {



                        Column(

                            modifier = Modifier.padding(20.dp)

                        ) {



                            Text(

                                text = category.nameBn,

                                style = MaterialTheme.typography.titleMedium


                            )



                            Spacer(
                                modifier = Modifier.height(4.dp)
                            )



                            Text(

                                text = category.nameEn,

                                color = Color.Gray

                            )



                        }



                    }



                }



            }



        }





        Divider()



        Spacer(
            modifier = Modifier.height(12.dp)
        )




        Text(

            text = "Quick Access",

            style = MaterialTheme.typography.titleMedium,

            fontWeight = FontWeight.Bold


        )



        Spacer(
            modifier = Modifier.height(10.dp)
        )




        Row(

            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement = Arrangement.SpaceEvenly


        ) {



            Text(

                text = "Bookings",

                modifier = Modifier.clickable {


                    navController.navigate(
                        Screen.MyBookings.route
                    )


                }


            )





            Text(

                text = "Profile",

                modifier = Modifier.clickable {


                    navController.navigate(Screen.Profile.route) {


                        launchSingleTop = true

                    }


                }


            )





            Text(

                text = "Logout",

                modifier = Modifier.clickable {



                    authViewModel.logout()



                    navController.navigate(
                        Screen.Welcome.route
                    ) {


                        popUpTo(0)

                        launchSingleTop = true


                    }



                }


            )




        }




    }



}
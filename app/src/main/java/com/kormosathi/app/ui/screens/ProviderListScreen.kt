package com.kormosathi.app.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController


@Composable
fun ProviderListScreen(
    navController: NavHostController,
    serviceName: String
) {


    val providers = listOf(

        "রাহুল ইলেকট্রিক সার্ভিস",
        "সুমন টেকনিক্যাল সার্ভিস",
        "বাবু কারিগর",
        "মিঠুন সার্ভিস সেন্টার"

    )



    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)

    ) {


        Text(

            text = serviceName,

            style = MaterialTheme.typography.headlineSmall

        )


        Spacer(
            modifier = Modifier.height(16.dp)
        )



        LazyColumn(

            verticalArrangement =
                Arrangement.spacedBy(12.dp)

        ) {



            items(providers) { provider ->



                Card(

                    modifier = Modifier

                        .fillMaxWidth()

                        .clickable {


                            // Next:
                            // Provider Details


                        },


                    elevation =
                        CardDefaults.cardElevation(4.dp)

                ) {



                    Column(

                        modifier =
                            Modifier.padding(18.dp)

                    ) {



                        Text(

                            text = provider,

                            style =
                                MaterialTheme.typography.titleMedium

                        )


                        Text(

                            text = "⭐ 4.5 Rating",

                            color =
                                MaterialTheme.colorScheme.primary

                        )


                    }


                }


            }


        }


    }


}
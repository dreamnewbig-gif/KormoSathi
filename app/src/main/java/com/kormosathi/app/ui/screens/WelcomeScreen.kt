package com.kormosathi.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.kormosathi.app.ui.navigation.Screen


@Composable
fun WelcomeScreen(
    navController: NavHostController
) {


    val bg = Brush.verticalGradient(

        colors = listOf(

            Color(0xFF0D47A1),
            Color(0xFF1565C0),
            Color(0xFF00897B)

        )

    )



    Column(

        modifier = Modifier
            .fillMaxSize()
            .background(bg),

        horizontalAlignment = Alignment.CenterHorizontally,

        verticalArrangement = Arrangement.Center

    ) {



        Text(

            text = "🔧",

            fontSize = 70.sp

        )



        Spacer(
            modifier = Modifier.height(16.dp)
        )



        Text(

            text = "কর্মসাথী",

            fontSize = 36.sp,

            fontWeight = FontWeight.Bold,

            color = Color(0xFFFFD54F)

        )



        Spacer(
            modifier = Modifier.height(8.dp)
        )



        Text(

            text = "আপনার এলাকার বিশ্বস্ত কারিগর",

            color = Color.White,

            fontSize = 18.sp

        )



        Spacer(
            modifier = Modifier.height(12.dp)
        )



        Text(

            text = "সার্ভিস নিন অথবা নিজের কাজের সুযোগ তৈরি করুন",

            color = Color.White,

            fontSize = 14.sp

        )



        Spacer(
            modifier = Modifier.height(40.dp)
        )



        Button(

            onClick = {

                navController.navigate(Screen.Login.route)

            },

            colors = ButtonDefaults.buttonColors(

                containerColor = Color(0xFFFFD54F)

            ),

            modifier = Modifier
                .width(220.dp)
                .height(50.dp)

        ) {


            Text(

                text = "📱 লগইন করুন",

                color = Color.Black,

                fontSize = 16.sp,

                fontWeight = FontWeight.Bold

            )


        }



    }


}
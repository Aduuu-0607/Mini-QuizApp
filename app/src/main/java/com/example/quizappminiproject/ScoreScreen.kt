package com.example.quizappminiproject

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun Score(
    score: Int,
    navController: NavHostController
) {
    val backgroundGradient = Brush.verticalGradient(
        listOf(Color(0xFF2193b0), Color(0xFF6dd5ed))
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGradient)
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "🎯 Quiz Completed!",
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(30.dp))

        Box(
            modifier = Modifier
                .size(150.dp)
                .shadow(10.dp, shape = CircleShape)
                .background(Color.White, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "$score / 5",
                fontSize = 36.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFF2193b0)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        val message = when (score) {
            5 -> "Excellent! 🌟🎉👏"
            4 -> "Great Job! 🥳💯"
            3 -> "Good Try! 👍😊"
            in 1..2 -> "Keep Practicing! 💪📘"
            else -> "Don't Give Up! 🔄😅"
        }

        AnimatedVisibility(visible = true, enter = fadeIn()) {
            Text(
                text = message,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Yellow,
                modifier = Modifier.padding(top = 12.dp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        /*✅ Try Again → navigate to "Quizscreen"
        Button(
            onClick = {
                navController.navigate("Quizscreen") {
                    popUpTo("quiz") { inclusive = true }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .padding(horizontal = 40.dp)
                .height(56.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "🔁 Try Again",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2193b0)
            )
        }

         */

        Spacer(modifier = Modifier.height(32.dp))

        // ✅ Next → go back to home
        Button(
            onClick = {
                navController.navigate("home") {
                    popUpTo("quiz") { inclusive = true }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .padding(horizontal = 40.dp)
                .height(56.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "🚀 Next",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2193b0)
            )
        }
    }
}

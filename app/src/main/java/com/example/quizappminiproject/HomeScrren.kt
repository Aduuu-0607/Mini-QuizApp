package com.example.quizappminiproject

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.quizappminiproject.R

@Composable
fun HomeScreen(navController: NavHostController) {

    val gradient = Brush.verticalGradient(
        colors = listOf(Color(0xFF2193b0), Color(0xFF6dd5ed))
    )

    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("quiz_prefs", Context.MODE_PRIVATE)
    val lastScore = sharedPreferences.getInt("last_score", 0)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // ✅ SVG Logo (converted to VectorDrawable)
        Image(
            painter = painterResource(id = R.drawable.hootsuite), // Your SVG icon file
            contentDescription = "App Logo",
            modifier = Modifier
                .size(150.dp)
                .padding(bottom = 24.dp)
        )

        // 🎉 Welcome Text
        Text(
            text = "🎉 Welcome to EngiCards",
            fontSize = 32.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 40.dp)
        )

        // 🚀 Start Quiz Button
        Button(
            onClick = { navController.navigate("bottom_nav_home") },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Text(
                text = "🚀 Start Quiz",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2193b0)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 📊 View Last Score
        Button(
            onClick = { navController.navigate("reviewscore/$lastScore") },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Text(
                text = "📊 View Last Score",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF2193b0)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ⚙️ Customize Quiz
        Button(
            onClick = { navController.navigate("customizeQuiz") },
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Text(
                text = "⚙️ Customize Quiz",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF2193b0)
            )
        }
    }
}

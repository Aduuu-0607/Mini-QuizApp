package com.example.quizappminiproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.google.firebase.FirebaseApp
import com.example.quizappminiproject.ui.theme.QuizAppMiniProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this) // Moved outside setContent

        enableEdgeToEdge()
        setContent {
            QuizAppMiniProjectTheme {
                NavGraph()
            }
        }
    }
}
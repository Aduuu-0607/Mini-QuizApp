package com.example.quizappminiproject

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun AIQuizScreen(navController: NavHostController) {
    val flashcards = listOf(
        Triple("🤖 Who is known as the father of Artificial Intelligence?", listOf("Alan Turing", "John McCarthy", "Geoffrey Hinton", "Andrew Ng"), "John McCarthy"),
        Triple("🧠 What does 'AI' stand for?", listOf("Automated Interface", "Artificial Intelligence", "Applied Informatics", "Algorithmic Integration"), "Artificial Intelligence"),
        Triple("📚 Which of the following is a type of machine learning?", listOf("Supervised Learning", "Reinforced Writing", "Guided Processing", "Linear Mapping"), "Supervised Learning"),
        Triple("🗣️ What is the name of the AI technique used for understanding human language?", listOf("NLP", "CNN", "RNN", "GAN"), "NLP"),
        Triple("🔄 Which AI algorithm is used for decision making based on rewards?", listOf("Supervised Learning", "Reinforcement Learning", "Unsupervised Learning", "Clustering"), "Reinforcement Learning")
    )

    var currentIndex by remember { mutableStateOf(0) }
    var showAnswer by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(-1) }
    var score by remember { mutableStateOf(0) }

    val (question, options, correctAnswer) = flashcards[currentIndex]

    val backgroundBrush = Brush.verticalGradient(
        listOf(Color(0xFFE1F5FE), Color(0xFFB3E5FC))
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = backgroundBrush)
            .padding(16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "🎓 Flashcard ${currentIndex + 1} of ${flashcards.size}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0277BD),
                modifier = Modifier.padding(bottom = 20.dp)
            )

            // Flashcard
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                shape = RoundedCornerShape(30.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (showAnswer) Color(0xFFFFF59D) else Color(0xFFB3E5FC)
                ),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    if (showAnswer) {
                        Text(
                            text = "✅ Answer: $correctAnswer",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF33691E),
                            textAlign = TextAlign.Center
                        )
                    } else {
                        Column {
                            Text(
                                text = question,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF01579B),
                                modifier = Modifier.padding(bottom = 16.dp),
                                textAlign = TextAlign.Center
                            )
                            options.forEachIndexed { index, option ->
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 6.dp),
                                    colors = CardDefaults.cardColors(
                                        containerColor = if (selectedOption == index)
                                            Color(0xFF81D4FA) else Color.White
                                    ),
                                    shape = RoundedCornerShape(20.dp),
                                    onClick = { selectedOption = index }
                                ) {
                                    Text(
                                        text = option,
                                        fontSize = 18.sp,
                                        color = Color.DarkGray,
                                        modifier = Modifier.padding(14.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { showAnswer = !showAnswer },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4FC3F7)),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = if (showAnswer) "🔙 Back to Question" else "👀 Show Answer",
                    fontSize = 18.sp,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        if (currentIndex > 0) {
                            currentIndex--
                            selectedOption = -1
                            showAnswer = false
                        }
                    },
                    enabled = currentIndex > 0,
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF64B5F6))
                ) {
                    Text("⬅ Previous", color = Color.White, fontSize = 16.sp)
                }

                Button(
                    onClick = {
                        if (!showAnswer && selectedOption != -1) {
                            val selectedAnswer = options[selectedOption]
                            if (selectedAnswer == correctAnswer) {
                                score++
                            }
                        }

                        if (currentIndex < flashcards.size - 1) {
                            currentIndex++
                            selectedOption = -1
                            showAnswer = false
                        } else {
                            navController.navigate("result/$score")
                        }
                    },
                    enabled = selectedOption != -1 || showAnswer,
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF64B5F6))
                ) {
                    Text("Next ➡", color = Color.White, fontSize = 16.sp)
                }
            }
        }
    }
}

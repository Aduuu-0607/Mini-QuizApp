package com.example.quizappminiproject

data class Flashcard(
    val id: String = "",
    val question: String = "",
    val optionA: String = "",
    val optionB: String = "",
    val optionC: String = "",
    val optionD: String = "",
    val correctAnswer: String = "",
    val topic: String = "" // e.g., "PHP", "Android", "AI"
)

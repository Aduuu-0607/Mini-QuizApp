package com.example.quizappminiproject

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
fun CustomizeQuizScreen(navController: NavHostController) {
    var flashcards by remember { mutableStateOf(listOf<Flashcard>()) }
    var showDialog by remember { mutableStateOf(false) }
    var editingCard by remember { mutableStateOf<Flashcard?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFFFFF3E0), Color(0xFFFFECB3))
                )
            )
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = { navController.navigate("home") }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }

                Text(
                    text = "⚙️ Customize Flashcards",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(end = 32.dp)
                )

                IconButton(onClick = {
                    editingCard = null
                    showDialog = true
                }) {
                    Icon(Icons.Default.Add, contentDescription = "Add Flashcard", tint = MaterialTheme.colorScheme.primary)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (flashcards.isEmpty()) {
                Text(
                    text = "No flashcards yet.\nTap ➕ to add one!",
                    color = Color.Gray,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 50.dp),
                    lineHeight = 20.sp
                )
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    items(flashcards) { card ->
                        FlashcardItem(
                            flashcard = card,
                            onEdit = {
                                editingCard = card
                                showDialog = true
                            },
                            onDelete = {
                                flashcards = flashcards.filter { it.id != card.id }
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { navController.navigate("home") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7043)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("🔙 Back to Home", fontSize = 16.sp)
            }
        }

        if (showDialog) {
            FlashcardDialog(
                initialCard = editingCard,
                onDismiss = { showDialog = false },
                onSave = { card ->
                    flashcards = if (editingCard != null) {
                        flashcards.map { if (it.id == card.id) card else it }
                    } else {
                        flashcards + card
                    }
                    showDialog = false
                }
            )
        }
    }
}

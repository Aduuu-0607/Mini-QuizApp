package com.example.quizappminiproject

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.util.*
import androidx.compose.foundation.clickable


@Composable
fun FlashcardDialog(
    initialCard: Flashcard? = null,
    onDismiss: () -> Unit,
    onSave: (Flashcard) -> Unit
) {
    var question by remember { mutableStateOf(initialCard?.question ?: "") }
    var optionA by remember { mutableStateOf(initialCard?.optionA ?: "") }
    var optionB by remember { mutableStateOf(initialCard?.optionB ?: "") }
    var optionC by remember { mutableStateOf(initialCard?.optionC ?: "") }
    var optionD by remember { mutableStateOf(initialCard?.optionD ?: "") }
    var correctAnswer by remember { mutableStateOf(initialCard?.correctAnswer ?: "") }
    var selectedTopic by remember { mutableStateOf(initialCard?.topic ?: "") }

    val topicOptions = listOf("PHP", "Android", "AI")

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(if (initialCard == null) "Add Flashcard" else "Edit Flashcard") },
        text = {
            Column {
                OutlinedTextField(value = question, onValueChange = { question = it }, label = { Text("Question") }, modifier = Modifier.fillMaxWidth())
                OutlinedTextField(value = optionA, onValueChange = { optionA = it }, label = { Text("Option A") }, modifier = Modifier.fillMaxWidth())
                OutlinedTextField(value = optionB, onValueChange = { optionB = it }, label = { Text("Option B") }, modifier = Modifier.fillMaxWidth())
                OutlinedTextField(value = optionC, onValueChange = { optionC = it }, label = { Text("Option C") }, modifier = Modifier.fillMaxWidth())
                OutlinedTextField(value = optionD, onValueChange = { optionD = it }, label = { Text("Option D") }, modifier = Modifier.fillMaxWidth())

                Spacer(modifier = Modifier.height(8.dp))
                Text("Correct Answer:")
                DropdownMenuBox(
                    selected = correctAnswer,
                    options = listOf(optionA, optionB, optionC, optionD),
                    onSelected = { correctAnswer = it }
                )

                Spacer(modifier = Modifier.height(8.dp))
                Text("Select Quiz Topic:")
                DropdownMenuBox(
                    selected = selectedTopic,
                    options = topicOptions,
                    onSelected = { selectedTopic = it }
                )
            }
        },
        confirmButton = {
            TextButton(onClick = {
                val updatedCard = Flashcard(
                    id = initialCard?.id ?: UUID.randomUUID().toString(),
                    question = question,
                    optionA = optionA,
                    optionB = optionB,
                    optionC = optionC,
                    optionD = optionD,
                    correctAnswer = correctAnswer,
                    topic = selectedTopic
                )
                onSave(updatedCard)
            }) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Composable
fun DropdownMenuBox(
    selected: String,
    options: List<String>,
    onSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(selected) }

    Box {
        OutlinedTextField(
            value = selectedOption,
            onValueChange = {},
            label = { Text("Select") },
            modifier = Modifier.fillMaxWidth(),
            readOnly = true
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        selectedOption = option
                        onSelected(option)
                        expanded = false
                    }
                )
            }
        }

        Spacer(modifier = Modifier
            .matchParentSize()
            .clickable { expanded = true }
        )
    }
}

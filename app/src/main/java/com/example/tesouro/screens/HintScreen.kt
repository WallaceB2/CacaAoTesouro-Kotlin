package com.example.tesouro.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun HintScreen(navController: NavController, hintNumber: Int) {
    val answer = remember { mutableStateOf("") }

    val hintText = when (hintNumber) {
        1 -> "Pista 1: Qual o nome do navio do Capitão Jack Sparrow?"
        2 -> "Pista 2: Onde está enterrado o coração de Davy Jones?"
        3 -> "Pista 3: Qual o nome da bússola que Jack usa?"
        else -> ""
    }

    val correctAnswer = when (hintNumber) {
        1 -> "Perola Negra"
        2 -> "Cofre de areia"
        3 -> "Bussola magica"
        else -> ""
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = hintText)
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = answer.value,
            onValueChange = { answer.value = it },
            label = { Text("Digite sua resposta") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Button(
                onClick = {
                    if (answer.value == correctAnswer) {
                        if (hintNumber < 3) navController.navigate("hint${hintNumber + 1}")
                        else navController.navigate("treasure")
                    }
                },
                enabled = answer.value == correctAnswer
            ) {
                Text(text = "Próxima Pista")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = { navController.popBackStack() }) {
                Text(text = "Voltar")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HintScreenPreview() {
    HintScreen(navController = rememberNavController(), hintNumber = 1)
}

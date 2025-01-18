package com.example.xogame.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun GameSetupScreen(navController: NavController) {
    var playerOne = remember { mutableStateOf("Player 1") }
    var playerTwo = remember { mutableStateOf("Player 2") }
    var boardSize = remember { mutableStateOf("3") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF2196F3), Color(0xFFBBDEFB))
                )
            ) // ไล่สีพื้นหลัง
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Game Setup",
            style = MaterialTheme.typography.headlineLarge.copy(color = Color.White),
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Player 1 Input
        CustomTextField(
            value = playerOne.value,
            onValueChange = { playerOne.value = it },
            label = "Player 1 Name"
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Player 2 Input
        CustomTextField(
            value = playerTwo.value,
            onValueChange = { playerTwo.value = it },
            label = "Player 2 Name"
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Board Size Input
        CustomTextField(
            value = boardSize.value,
            onValueChange = {
                if (it.all { char -> char.isDigit() }) {
                    boardSize.value = it
                }
            },
            label = "Board Size",
            keyboardType = KeyboardType.Number
        )
        Spacer(modifier = Modifier.height(32.dp))

        // Start Game Button
        Button(
            onClick = {
                val boardSizeInt = boardSize.value.toIntOrNull() ?: 3
                navController.navigate("game/${playerOne.value}/${playerTwo.value}/$boardSizeInt")
            },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(50.dp)
                .clip(RoundedCornerShape(16.dp)),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1E88E5),
                contentColor = Color.White
            )
        ) {
            Text(text = "Start Game", style = MaterialTheme.typography.bodyLarge)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Back Button
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(50.dp)
                .clip(RoundedCornerShape(16.dp)),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF90CAF9),
                contentColor = Color.Black
            )
        ) {
            Text(text = "Back", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium.copy(color = Color(0xFF0D47A1))
            )
        },
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color(0xFF1976D2), // เส้นขอบเมื่อโฟกัส
            unfocusedIndicatorColor = Color.Gray, // เส้นขอบเมื่อไม่ได้โฟกัส
            focusedTextColor = Color.Black, // สีข้อความ
            unfocusedTextColor = Color.Black,
            cursorColor = Color(0xFF1976D2), // สีเคอร์เซอร์
            containerColor = Color.White // สีพื้นหลัง
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = keyboardType,
            imeAction = ImeAction.Done
        ),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(12.dp))
    )
}

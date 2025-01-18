package com.example.xogame.screen

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.xo_game.viewmodel.GameHistoryViewModel
import com.example.xogame.sqlite.GameHistoryEntity

@Composable
fun GameScreen(
    playerOne: String,
    playerTwo: String,
    boardSize: Int,
    navController: NavController,
    viewModel: GameHistoryViewModel = hiltViewModel()
) {
    val screenWidth = androidx.compose.ui.platform.LocalConfiguration.current.screenWidthDp
    val cellSize = (screenWidth - 64) / boardSize
    val datePlayed = System.currentTimeMillis()
    var board = remember {
        mutableStateListOf(*Array(boardSize) { MutableList(boardSize) { "" } })
    }
    var currentPlayer = remember { mutableStateOf(playerOne) }
    var winner = remember { mutableStateOf("") }
    val fontXOSize = (screenWidth - 92) / boardSize

    LaunchedEffect(winner.value) {
        if (winner.value.isNotEmpty()) {
            val resultText = if (winner.value == "Draw") "Draw" else "${winner.value} Winner"
            viewModel.addHistory(
                GameHistoryEntity(
                    player1 = playerOne,
                    player2 = playerTwo,
                    result = resultText,
                    datePlayed = datePlayed
                )
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE3F2FD)), // สีพื้นหลัง
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Current player's turn
        Text(
            "Current Turn: ${currentPlayer.value}",
            style = MaterialTheme.typography.headlineMedium.copy(color = Color(0xFF0D47A1))
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Drawing the board
        LazyColumn(
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            items(board.size) { row ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    for (col in 0 until boardSize) {
                        Box(
                            modifier = Modifier
                                .size(cellSize.dp)
                                .border(2.dp, Color(0xFF1976D2), RoundedCornerShape(8.dp)) // เส้นขอบมุมโค้ง
                                .background(
                                    if (board[row][col].isEmpty()) Color(0xFFBBDEFB) else Color(0xFF64B5F6),
                                    RoundedCornerShape(8.dp)
                                ) // สีพื้นหลังช่อง
                                .clickable(enabled = board[row][col].isEmpty() && winner.value.isEmpty()) {
                                    board[row][col] =
                                        if (currentPlayer.value == playerOne) "X" else "O"
                                    if (checkWinner(board, boardSize)) {
                                        winner.value = currentPlayer.value
                                    } else if (checkDraw(board)) {
                                        winner.value = "Draw"
                                    } else {
                                        currentPlayer.value =
                                            if (currentPlayer.value == playerOne) playerTwo else playerOne
                                    }
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                board[row][col],
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontSize = fontXOSize.sp,
                                    color = Color.White
                                )
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display winner text
        if (winner.value.isNotEmpty()) {
            Text(
                text = if (winner.value == "Draw") "It's a Draw!" else "${winner.value} wins!",
                style = MaterialTheme.typography.headlineLarge,
                color = if (winner.value == "Draw") Color.Gray else Color(0xFF43A047)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Back to menu button
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .fillMaxWidth(0.7f) // ปุ่มกว้าง 70% ของหน้าจอ
                .clip(RoundedCornerShape(16.dp)), // ปุ่มมุมโค้ง
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF64B5F6), // สีพื้นหลังปุ่ม
                contentColor = Color.White // สีข้อความในปุ่ม
            )
        ) {
            Text("Back to Menu")
        }
    }
}


fun checkDraw(board: List<List<String>>): Boolean {
    for (x in board) {
        for (y in x) {
            if (y.isEmpty()) {
                return false
            }
        }
    }
    return true
}
fun checkWinner(board: List<List<String>>, boardSize: Int): Boolean {
    // ตรวจสอบแถว (Rows)
    for (x in 0 until boardSize) {
        var rowWinner = true
        for (y in 1 until boardSize) {
            if (board[x][y] != board[x][y-1] || board[x][y].isEmpty()) {
                rowWinner = false
                break
            }
        }
        if (rowWinner && board[x][0].isNotEmpty()) {
            Log.d(TAG, "Winner found in row: $x")
            return true
        }
    }

    // ตรวจสอบคอลัมน์ (Columns)
    for (y in 0 until boardSize) {
        var colWinner = true
        for (x in 1 until boardSize) {
            if (board[x][y] != board[x-1][y] || board[x][y].isEmpty()) {
                colWinner = false
                break
            }
        }
        if (colWinner && board[0][y].isNotEmpty()) {
            Log.d(TAG, "Winner found in column: $y")
            return true
        }
    }

    // ตรวจสอบเส้นทแยงมุม (Main Diagonal)
    var mainDiagWinner = true
    for (i in 1 until boardSize) {
        if (board[i][i] != board[i-1][i-1] || board[i][i].isEmpty()) {
            mainDiagWinner = false
            break
        }
    }
    if (mainDiagWinner && board[0][0].isNotEmpty()) {
        Log.d(TAG, "Winner found in main diagonal")
        return true
    }

    // ตรวจสอบเส้นทแยงมุมย่อย (Anti Diagonal)
    var antiDiagWinner = true
    for (i in 1 until boardSize) {
        if (board[i][boardSize-1-i] != board[i-1][boardSize-i] || board[i][boardSize-1-i].isEmpty()) {
            antiDiagWinner = false
            break
        }
    }
    if (antiDiagWinner && board[0][boardSize-1].isNotEmpty()) {
        Log.d(TAG, "Winner found in anti diagonal")
        return true
    }

    return false // ไม่มีผู้ชนะ
}
package com.example.xogame.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.xo_game.viewmodel.GameHistoryViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun GameHistoryPlayScreen(
    navController: NavController,
    viewModel: GameHistoryViewModel = hiltViewModel()
) {
    val historyList by viewModel.histories.observeAsState(emptyList())

    val uniqueHistoryList = remember(historyList) {
        historyList.distinctBy { it.id }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE3F2FD))
            .padding(16.dp),// สีพื้นหลัง
        verticalArrangement = Arrangement.Top
    ) {
        // Back Button
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .fillMaxWidth() // ปุ่มกว้าง 70% ของหน้าจอ
                .clip(RoundedCornerShape(16.dp)), // ปุ่มมุมโค้ง
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF64B5F6), // สีพื้นหลังปุ่ม
                contentColor = Color.White // สีข้อความในปุ่ม
            )
        ) {
            Text("Back")
        }

        // Title
        Text(
            text = "Game History",
            style = MaterialTheme.typography.headlineMedium.copy(color = Color(0xFF0D47A1)),
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.CenterHorizontally)
        )

        // Render UI
        if (historyList.isEmpty()) {
            // Show fallback text when no history is available
            Text(
                text = "No game history available.",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Gray,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight(Alignment.CenterVertically),
                textAlign = TextAlign.Center
            )
        } else {
            // Render history list
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(historyList) { history ->
                    Card(
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFBBDEFB) // สีพื้นหลังของการ์ด
                        ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Text(
                                text = "Player 1: ${history.player1}",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color(0xFF0D47A1) // สีข้อความ
                            )
                            Text(
                                text = "Player 2: ${history.player2}",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color(0xFF0D47A1)
                            )
                            Text(
                                text = "Result: ${history.result}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color(0xFF1976D2) // สีข้อความที่แสดงผลลัพธ์
                            )
                            Text(
                                text = "Date: ${formatDate(history.datePlayed)}",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Gray
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun formatDate(timestamp: Long): String {
    val formatter = remember { SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault()) }
    return formatter.format(Date(timestamp))
}



package com.example.xogame.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun MenuScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(colors = listOf(Color(0xFF2196F3), Color(0xFFBBDEFB))
            )) // ไล่สีพื้นหลัง
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp) // ระยะห่างระหว่างองค์ประกอบ
        ) {
            // โลโก้ หรือไอคอนเกม
            Icon(
                imageVector = Icons.Default.Clear, // ไอคอนที่สื่อถึงเกม
                contentDescription = "Game Icon",
                modifier = Modifier
                    .size(96.dp)
                    .background(Color.White, CircleShape) // วงกลมรอบไอคอน
                    .padding(16.dp),
                tint = Color(0xFF2196F3)
            )

            // ข้อความต้อนรับ
            Text(
                text = "Welcome to Tic-Tac-Toe Game",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0D47A1) // สีฟ้าเข้ม
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            // ปุ่มเริ่มเกม
            Button(
                onClick = { navController.navigate("gameSetup") },
                modifier = Modifier
                    .fillMaxWidth(0.7f) // ปุ่มกว้าง 70% ของหน้าจอ
                    .clip(RoundedCornerShape(16.dp)), // ปุ่มมุมโค้ง
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF64B5F6), // สีพื้นหลังปุ่ม
                    contentColor = Color.White // สีข้อความในปุ่ม
                )
            ) {
                Text("Start Game")
            }

            // ปุ่มดูประวัติ
            Button(
                onClick = { navController.navigate("gameHistory") },
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .clip(RoundedCornerShape(16.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1976D2),
                    contentColor = Color.White
                )
            ) {
                Text("View History")
            }
        }
    }
}

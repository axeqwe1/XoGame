package com.example.xogame.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.xogame.screen.GameHistoryPlayScreen
import com.example.xogame.screen.GameScreen
import com.example.xogame.screen.GameSetupScreen
import com.example.xogame.screen.MenuScreen
import com.example.xo_game.viewmodel.GameHistoryViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "menu") {
        // กำหนดหน้าจอแรกที่จะแสดง
        composable("menu") { MenuScreen(navController) }

        // กำหนดเส้นทางของหน้าจอที่สอง
        composable("gameSetup") { GameSetupScreen(navController) }

        // กำหนดเส้นทางสำหรับหน้าจอเกม โดยมีการรับค่า argument
        composable("game/{playerOne}/{playerTwo}/{boardSize}") { backStackEntry ->
            val playerOne = backStackEntry.arguments?.getString("playerOne") ?: "Player 1"
            val playerTwo = backStackEntry.arguments?.getString("playerTwo") ?: "Player 2"
            val boardSize = backStackEntry.arguments?.getString("boardSize")?.toIntOrNull() ?: 3
            GameScreen(playerOne, playerTwo, boardSize, navController)
        }

        // กำหนดเส้นทางสำหรับหน้าจอ Game History โดยใช้ ViewModel
        composable("gameHistory") {
            // ใช้ hiltViewModel() เพื่อดึง ViewModel สำหรับการใช้ในหน้าจอนี้
            GameHistoryPlayScreen(navController)
        }
    }
}

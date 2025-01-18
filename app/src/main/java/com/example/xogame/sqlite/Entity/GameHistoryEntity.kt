package com.example.xogame.sqlite

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_history")
data class GameHistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val player1: String,
    val player2: String,
    val result: String,
    val datePlayed: Long
)

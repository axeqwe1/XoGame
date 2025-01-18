package com.example.xogame.sqlite

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GameHistoryDAO {
    @Query("SELECT * FROM game_history ORDER BY id DESC")
    fun getAllHistories(): LiveData<List<GameHistoryEntity>>

    @Insert
    fun insertHistory(history: GameHistoryEntity)

    @Delete
    fun deleteHistory(history: GameHistoryEntity)
}


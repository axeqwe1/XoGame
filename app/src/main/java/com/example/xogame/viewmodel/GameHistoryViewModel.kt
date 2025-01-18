package com.example.xo_game.viewmodel


import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.xogame.sqlite.AppDatabase
import com.example.xogame.sqlite.GameHistoryDAO
import com.example.xogame.sqlite.GameHistoryEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameHistoryViewModel @Inject constructor(
    private val gameHistoryDao: GameHistoryDAO
) : ViewModel() {
    val histories: LiveData<List<GameHistoryEntity>> = gameHistoryDao.getAllHistories()

    fun addHistory(history: GameHistoryEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            gameHistoryDao.insertHistory(history)
        }
    }

    fun deleteHistory(history: GameHistoryEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            gameHistoryDao.deleteHistory(history)
        }
    }
}

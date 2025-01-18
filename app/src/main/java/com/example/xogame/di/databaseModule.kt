package com.example.xogame.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.xogame.sqlite.AppDatabase
import com.example.xogame.sqlite.GameHistoryDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
//        val MIGRATION_1_2 = object : Migration(1,2){
//            override fun migrate(db: SupportSQLiteDatabase) {
//                // ตัวอย่าง: เพิ่มคอลัมน์ใหม่ในตาราง game_history
//            }
//        }
        println("Providing database instance")
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "game_history_db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideGameHistoryDao(database: AppDatabase): GameHistoryDAO {
        return database.gameHistoryDao()
    }
}

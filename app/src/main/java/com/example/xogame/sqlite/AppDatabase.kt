package com.example.xogame.sqlite

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [GameHistoryEntity::class],
    version = 2, // ใส่ version ที่ต้องการ
    exportSchema = false // ใช้ false หากไม่ต้องการ export schema
)

abstract class AppDatabase : RoomDatabase()  {
    abstract fun gameHistoryDao(): GameHistoryDAO

    companion object {
//        val MIGRATION_1_2 = object : Migration(1,2){
//            override fun migrate(db: SupportSQLiteDatabase) {
//                // ตัวอย่าง: เพิ่มคอลัมน์ใหม่ในตาราง game_history
//            }
//        }
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "game_history_db"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
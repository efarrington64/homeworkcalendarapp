package com.homecalapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/** As seen in https://github.com/android/sunflower. */
@Database(entities = [Homework::class], version = 1, exportSchema = false)
abstract class HcaDatabase : RoomDatabase() {
    abstract fun homeworks(): Homeworks

    companion object {
        // For Singleton instantiation
        @Volatile private var instance: HcaDatabase? = null

        fun getInstance(context: Context): HcaDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Create and pre-populate the database. See this article for more details:
        // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
        private fun buildDatabase(context: Context): HcaDatabase =
            Room.databaseBuilder(context, HcaDatabase::class.java, "plano-db")
                .allowMainThreadQueries()
                .build()
    }
}

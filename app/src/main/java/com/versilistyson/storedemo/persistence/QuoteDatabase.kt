package com.versilistyson.storedemo.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [QuoteData::class], version = 1, exportSchema = false)
abstract class QuoteDatabase: RoomDatabase() {
    abstract fun quoteDao(): QuoteDao

    companion object {
        @Volatile
        private var INSTANCE: QuoteDatabase? = null

        fun getInstance(context: Context): QuoteDatabase {
            val db by lazy {
                Room.databaseBuilder(
                    context,
                    QuoteDatabase::class.java,
                    "quote_database"
                ).fallbackToDestructiveMigration().build()
            }
            return db
        }
    }
}
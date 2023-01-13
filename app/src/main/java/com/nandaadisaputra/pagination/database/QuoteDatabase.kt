package com.nandaadisaputra.pagination.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nandaadisaputra.pagination.api.QuoteResponseItem
import com.nandaadisaputra.pagination.data.QuoteDao

@Database(
    entities = [QuoteResponseItem::class],
    version = 1,
    exportSchema = false
)
abstract class QuoteDatabase : RoomDatabase() {

    abstract fun quoteDao(): QuoteDao

    companion object {
        @Volatile
        private var INSTANCE: QuoteDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): QuoteDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    QuoteDatabase::class.java, "quote_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}
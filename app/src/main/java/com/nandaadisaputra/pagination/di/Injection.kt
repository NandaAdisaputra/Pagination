package com.nandaadisaputra.pagination.di

import android.content.Context
import com.nandaadisaputra.pagination.api.ApiConfig
import com.nandaadisaputra.pagination.data.QuoteRepository
import com.nandaadisaputra.pagination.database.QuoteDatabase

object Injection {
    fun provideRepository(context: Context): QuoteRepository {
        val database = QuoteDatabase.getDatabase(context)
        val apiService = ApiConfig.getApiService()
        return QuoteRepository(database, apiService)
    }
}
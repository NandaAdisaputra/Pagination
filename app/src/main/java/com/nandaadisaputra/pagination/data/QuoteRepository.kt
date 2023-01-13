package com.nandaadisaputra.pagination.data

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.nandaadisaputra.pagination.api.ApiService
import com.nandaadisaputra.pagination.api.QuoteResponseItem
import com.nandaadisaputra.pagination.database.QuoteDatabase

class QuoteRepository(private val quoteDatabase: QuoteDatabase, private val apiService: ApiService) {
    @OptIn(ExperimentalPagingApi::class)
    fun getQuote(): LiveData<PagingData<QuoteResponseItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            remoteMediator = QuoteRemoteMediator(quoteDatabase, apiService),
            pagingSourceFactory = {
//                QuotePagingSource(apiService)
            quoteDatabase.quoteDao().getAllQuote()
        }
        ).liveData
    }
}
package com.nandaadisaputra.pagination.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.nandaadisaputra.pagination.api.ApiService
import com.nandaadisaputra.pagination.api.QuoteResponseItem
import com.nandaadisaputra.pagination.database.QuoteDatabase

@OptIn(ExperimentalPagingApi::class)
class QuoteRemoteMediator(
    private val database: QuoteDatabase,
    private val apiService: ApiService
) : RemoteMediator<Int, QuoteResponseItem>() {

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, QuoteResponseItem>
    ): MediatorResult {
        val page = INITIAL_PAGE_INDEX
        try {
            val responseData = apiService.getQuote(page, state.config.pageSize)
            val endOfPaginationReached = responseData.isEmpty()
            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.quoteDao().deleteAll()
                }
                database.quoteDao().insertQuote(responseData)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: Exception) {
            return MediatorResult.Error(exception)
        }
    }

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }
}
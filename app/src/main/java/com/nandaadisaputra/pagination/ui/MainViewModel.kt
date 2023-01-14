package com.nandaadisaputra.pagination.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.nandaadisaputra.pagination.api.QuoteResponseItem
import com.nandaadisaputra.pagination.base.viewmodel.BaseViewModel
import com.nandaadisaputra.pagination.data.QuoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val quoteRepository: QuoteRepository
) : BaseViewModel() {
    val quote: LiveData<PagingData<QuoteResponseItem>> =
        quoteRepository.getQuote().cachedIn(viewModelScope)
}
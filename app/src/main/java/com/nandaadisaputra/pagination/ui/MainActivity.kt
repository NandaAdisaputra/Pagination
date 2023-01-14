package com.nandaadisaputra.pagination.ui

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.nandaadisaputra.pagination.R
import com.nandaadisaputra.pagination.adapter.LoadingStateAdapter
import com.nandaadisaputra.pagination.adapter.QuoteListAdapter
import com.nandaadisaputra.pagination.api.QuoteResponseItem
import com.nandaadisaputra.pagination.base.activity.BaseActivity
import com.nandaadisaputra.pagination.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.rvQuote.layoutManager = LinearLayoutManager(this)
        getData()
    }
    private fun getData() {
        val adapter = QuoteListAdapter()
        binding.rvQuote.adapter = adapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                adapter.retry()
            }
        )
        viewModel.quote.observe(this) {
            adapter.submitData(lifecycle, it)
        }
    }
}

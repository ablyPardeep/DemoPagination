package com.ably.demopagination.viewmodel

import com.ably.demopagination.network.ApiService
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.ably.demopagination.paging.PagedDataSource

class DataViewModel(private val api: ApiService) : ViewModel() {
    val passengers = Pager(PagingConfig(pageSize = 10)) {
        PagedDataSource(api)
    }.flow.cachedIn(viewModelScope)
}
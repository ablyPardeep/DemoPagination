package com.ably.demopagination.viewmodel

import com.ably.demopagination.network.ApiService
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class DataViewModelFactory(private val api: ApiService) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DataViewModel(api) as T
    }
}
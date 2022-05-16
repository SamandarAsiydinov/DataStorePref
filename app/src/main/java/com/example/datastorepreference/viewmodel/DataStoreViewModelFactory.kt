package com.example.datastorepreference.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.datastorepreference.repository.DataStorePrefRepository

@Suppress("UNCHECKED_CAST")
class DataStoreViewModelFactory(
    private val dataStorePrefRepository: DataStorePrefRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DataStoreViewModel::class.java)) {
            return DataStoreViewModel(dataStorePrefRepository) as T
        }
        throw IllegalStateException()
    }
}
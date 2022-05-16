package com.example.datastorepreference.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.datastorepreference.repository.DataStorePrefRepository
import kotlinx.coroutines.launch

class DataStoreViewModel(
    private val dataStoreRep: DataStorePrefRepository
): ViewModel() {
    private val _userName = MutableLiveData("")
    val userName: LiveData<String> = _userName

    init {
        viewModelScope.launch {
            dataStoreRep.getUserName.collect {
                _userName.value = it
            }
        }
    }
    suspend fun saveUserName(userName: String) {
        dataStoreRep.setUserName(userName)
    }
}
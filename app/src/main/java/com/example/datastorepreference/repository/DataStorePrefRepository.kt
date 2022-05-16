package com.example.datastorepreference.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStorePrefRepository(context: Context) {

    private val dataStore: DataStore<Preferences> = context.createDataStore(name = "DataStore")

    private val userNameDefault = ""

    companion object {
        val prefUserName = preferencesKey<String>("user_name")

        private var INSTANCE: DataStorePrefRepository? = null

        fun getInstance(context: Context): DataStorePrefRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE?.let {
                    return it
                }
                val instance = DataStorePrefRepository(context)
                INSTANCE = instance
                instance
            }
        }
    }

    suspend fun setUserName(userName: String) {
        dataStore.edit { preference ->
            preference[prefUserName] = userName
        }
    }

    val getUserName: Flow<String> = dataStore.data
        .map { preference ->
            preference[prefUserName] ?: userNameDefault
        }
}
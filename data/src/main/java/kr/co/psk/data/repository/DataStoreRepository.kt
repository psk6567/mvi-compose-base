package kr.co.psk.data.repository

import kotlinx.coroutines.flow.Flow

interface  DataStoreRepository {
    suspend fun setTestPreferences(testString : String)
    suspend fun getTestPreferences() : Flow<String>
}
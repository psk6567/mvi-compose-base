package kr.co.psk.data.datasource.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import kr.co.psk.common.AES256Cipher

class DataStoreDataSource(private val context : Context) {

    private val Context.dataStore by preferencesDataStore(name = "test_pref")

    companion object {
        private val KET_TEST_STRING = stringPreferencesKey("test_string")
    }

    suspend fun setTestPreferences(testString : String) {
        context.dataStore.edit {dataStore ->
            dataStore[KET_TEST_STRING] = AES256Cipher.encode(testString)
        }
    }

    suspend fun getTestPreferences() = context.dataStore.data.map {
        AES256Cipher.decode(it[KET_TEST_STRING] ?: "")
    }
}
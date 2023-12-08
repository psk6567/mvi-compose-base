package kr.co.psk.data.mapper

import kotlinx.coroutines.flow.Flow
import kr.co.psk.data.datasource.datastore.DataStoreDataSource
import kr.co.psk.data.repository.DataStoreRepository
import javax.inject.Inject

class DataStoreMapper @Inject constructor(
    private val dataStoreDataSource: DataStoreDataSource
) : DataStoreRepository {
    override suspend fun setTestPreferences(testString: String) {
        dataStoreDataSource.setTestPreferences(testString)
    }

    override suspend fun getTestPreferences(): Flow<String> = dataStoreDataSource.getTestPreferences()


}
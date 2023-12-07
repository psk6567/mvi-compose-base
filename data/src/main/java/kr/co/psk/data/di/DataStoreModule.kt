package kr.co.psk.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kr.co.psk.data.datasource.datastore.DataStoreDataSource
import kr.co.psk.data.mapper.DataStoreMapper
import kr.co.psk.data.repository.DataStoreRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataStoreModule {

    @Provides
    @Singleton
    fun provideDataStoreDataSource(@ApplicationContext context: Context): DataStoreDataSource {
        return DataStoreDataSource(context)
    }

    @Provides
    @Singleton
    fun provideDataStoreRepository(
        dataStoreDataSource: DataStoreDataSource
    ): DataStoreRepository {
        return DataStoreMapper(dataStoreDataSource)
    }
}
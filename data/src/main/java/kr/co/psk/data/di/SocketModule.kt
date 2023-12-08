package kr.co.psk.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kr.co.psk.common.di.ApplicationIoScope
import kr.co.psk.data.datasource.datastore.DataStoreDataSource
import kr.co.psk.data.datasource.socket.SocketDataSource
import kr.co.psk.data.mapper.DataStoreMapper
import kr.co.psk.data.mapper.SocketMapper
import kr.co.psk.data.repository.DataStoreRepository
import kr.co.psk.data.repository.SocketRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SocketModule {
    @Provides
    @Singleton
    fun provideSocketDataSource(@ApplicationIoScope ioScope : CoroutineScope): SocketDataSource {
        return SocketDataSource(ioScope)
    }

    @Provides
    @Singleton
    fun provideSocketRepository(
        socketDataSource: SocketDataSource
    ): SocketRepository {
        return SocketMapper(socketDataSource)
    }

}
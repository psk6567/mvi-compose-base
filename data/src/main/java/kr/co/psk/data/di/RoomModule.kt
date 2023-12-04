package kr.co.psk.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kr.co.psk.data.datasource.room.RoomDataSource
import kr.co.psk.data.datasource.room.RoomSampleDB
import kr.co.psk.data.mapper.RoomMapper
import kr.co.psk.data.repository.RoomRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context : Context) : RoomSampleDB =
        Room.databaseBuilder(context,RoomSampleDB::class.java, "SampleDB")
            .build()

    @Provides
    @Singleton
    fun provideDao(database : RoomSampleDB) = database.sampleDao()

    @Provides
    @Singleton
    fun provideRoomRepository(
        roomDataSource: RoomDataSource
    ): RoomRepository {
        return RoomMapper(roomDataSource)
    }
}
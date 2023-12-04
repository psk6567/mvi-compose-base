package kr.co.psk.data.mapper

import androidx.room.RoomDatabase
import kr.co.psk.data.datasource.retrofit2.Retrofit2DataSource
import kr.co.psk.data.datasource.room.RoomDataSource
import kr.co.psk.data.model.entity.Retrofit2TestEntity
import kr.co.psk.data.model.entity.RoomTestEntity
import kr.co.psk.data.repository.Retrofit2Repository
import kr.co.psk.data.repository.RoomRepository
import javax.inject.Inject

class RoomMapper @Inject constructor(
    private val roomDataSource: RoomDataSource
) : RoomRepository{
    override suspend fun insert(roomTestEntity: RoomTestEntity) {
        roomDataSource.insert(roomTestEntity.asDto())
    }

    override suspend fun select(): List<RoomTestEntity> = roomDataSource.select().map { it.asEntity() }

    override suspend fun delete() {
        roomDataSource.delete()
    }
}
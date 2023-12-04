package kr.co.psk.data.repository

import kr.co.psk.data.model.entity.RoomTestEntity

interface  RoomRepository {
    suspend fun insert(roomTestEntity: RoomTestEntity)
    suspend fun select() : List<RoomTestEntity>
    suspend fun delete()
}
package kr.co.psk.data.datasource.room

import androidx.room.Database
import androidx.room.RoomDatabase
import kr.co.psk.data.model.dto.RoomTestDto

@Database(
    entities = [RoomTestDto::class],
    version = 1
)
abstract class RoomSampleDB : RoomDatabase() {
    abstract fun sampleDao() : RoomDataSource
}


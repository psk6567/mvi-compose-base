package kr.co.psk.data.datasource.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kr.co.psk.common.BuildConfig
import kr.co.psk.data.model.dto.RoomTestDto

@Dao
interface RoomDataSource {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(roomTestDto : RoomTestDto)

    @Query("SELECT * FROM ${BuildConfig.SAMPLE_TABLE_NAME}")
    suspend fun select() : List<RoomTestDto>

    @Query("DELETE FROM ${BuildConfig.SAMPLE_TABLE_NAME}")
    suspend fun delete()
}
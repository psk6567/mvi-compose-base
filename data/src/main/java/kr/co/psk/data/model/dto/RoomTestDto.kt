package kr.co.psk.data.model.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kr.co.psk.common.BuildConfig
import kr.co.psk.data.model.entity.RoomTestEntity

@Entity(tableName = BuildConfig.SAMPLE_TABLE_NAME)
data class RoomTestDto(
    @PrimaryKey(autoGenerate = true) val index : Long = 0,
    @ColumnInfo val id : String,
    @ColumnInfo val userName : String,
    @ColumnInfo val age : Int,
) {
    fun asEntity() : RoomTestEntity = RoomTestEntity(
        idx = index,
        id = id,
        userName = userName,
        age = age
    )
}
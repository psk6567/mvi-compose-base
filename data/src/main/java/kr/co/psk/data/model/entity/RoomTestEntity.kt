package kr.co.psk.data.model.entity

import kr.co.psk.data.model.dto.RoomTestDto

data class RoomTestEntity (
    val idx : Long = 0,
    val id : String,
    val userName : String,
    val age : Int
) {
    fun asDto()  = RoomTestDto(
        id = id,
        userName = userName,
        age = age
    )
}
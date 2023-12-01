package kr.co.psk.data.model.dto

import kr.co.psk.data.model.entity.Retrofit2TestEntity

data class Retrofit2TestDto (
    val userId : Int,
    val id : Long,
    val title : String,
    val body : String
) {
    fun asEntity() : Retrofit2TestEntity = Retrofit2TestEntity(
        userId = userId,
        id = id,
        title = title,
        body = body
    )
}
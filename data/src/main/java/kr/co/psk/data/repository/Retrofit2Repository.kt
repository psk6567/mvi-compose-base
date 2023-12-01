package kr.co.psk.data.repository

import kr.co.psk.data.model.entity.Retrofit2TestEntity

interface  Retrofit2Repository {
    suspend fun getRetrofit2Test() : List<Retrofit2TestEntity>
}
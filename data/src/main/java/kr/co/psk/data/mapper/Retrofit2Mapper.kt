package kr.co.psk.data.mapper

import kr.co.psk.data.datasource.retrofit2.Retrofit2DataSource
import kr.co.psk.data.model.entity.Retrofit2TestEntity
import kr.co.psk.data.repository.Retrofit2Repository
import javax.inject.Inject

class Retrofit2Mapper @Inject constructor(
    private val retrofit2DataSource : Retrofit2DataSource
) : Retrofit2Repository{
    override suspend fun getRetrofit2Test(): List<Retrofit2TestEntity>  =
        retrofit2DataSource.getRetrofit2Test().map {
            it.asEntity()
        }
}
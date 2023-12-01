package kr.co.psk.data.datasource.retrofit2

import kr.co.psk.data.model.dto.Retrofit2TestDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

//API List


interface Retrofit2Service {

   @GET("posts")
   suspend fun getRetrofit2Test (
   ): Response<List<Retrofit2TestDto>>

}
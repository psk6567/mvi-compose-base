package kr.co.psk.data.datasource.retrofit2

import kr.co.psk.data.model.dto.Retrofit2TestDto
import retrofit2.Response
import javax.inject.Inject


class Retrofit2DataSource @Inject constructor(
    private val retrofit2Service: Retrofit2Service
) {

    inline fun <reified T> Response<T>.response(
        onSuccess: (T) -> Unit,
        onFailed: (Int, String) -> Unit
    ) {
        val response = this
        if (response.isSuccessful && response.body() != null) {
            try {
                response.body()?.let { onSuccess(it) } ?: onFailed(
                    response.code(),
                    response.errorBody().toString()
                )
            } catch (e: Exception) {
                e.printStackTrace()
                onFailed(-1, "ERROR")
            }
        } else {
            onFailed(response.code(), response.errorBody().toString())
        }
    }

    inline fun <reified T> Response<T>.response(): T? {
        val response = this
        if (response.isSuccessful && response.body() != null) {
            try {
                val _response = response.body()?.let { it }
                return _response
            } catch (e: Exception) {
                e.printStackTrace()
                return null
            }
        } else {
            return null
        }
    }

    suspend fun getRetrofit2Test(): List<Retrofit2TestDto> {
        return retrofit2Service.getRetrofit2Test().response() ?: emptyList()
    }
}

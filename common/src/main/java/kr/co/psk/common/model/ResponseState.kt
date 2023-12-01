package kr.co.psk.common.model

import androidx.annotation.Keep
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Keep
sealed class ResponseState<out T> {

    @Keep
    object Loading : ResponseState<Nothing>()

    @Keep
    data class Success<T>(val data: T) : ResponseState<T>()

    @Keep
    data class Failure(val error: Throwable = Throwable()) : ResponseState<Nothing>()

}


suspend fun <T> ResponseState<T>.onLoading(
    onLoading: suspend () -> Unit,
) : ResponseState<T> {
    return withContext(Dispatchers.Main) {
        if (this@onLoading is ResponseState.Loading) {
            onLoading()
        }
        this@onLoading
    }
}

suspend fun <T> ResponseState<T>.onSuccess(
    onSuccess: suspend (T) -> Unit,
) : ResponseState<T> {
    if (this@onSuccess is ResponseState.Success<T>) {
        onSuccess(this@onSuccess.data)
    }
    return this@onSuccess
//    return withContext(Dispatchers.Main) {
//        if (this@onSuccess is ResponseState.Success<T>) {
//            onSuccess(this@onSuccess.data)
//        }
//        this@onSuccess
//    }
}

suspend fun <T> ResponseState<T>.onFailed(
    onFailure: suspend (Throwable) -> Unit,
) : ResponseState<T>  {
    return withContext(Dispatchers.Main) {
        if (this@onFailed is ResponseState.Failure) {
            onFailure(this@onFailed.error)
        }
        this@onFailed
    }
}

fun <T> ResponseState<T>.getData(): T? = if (this is ResponseState.Success<T>) {
    this.data
} else
    null
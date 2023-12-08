package kr.co.psk.data.repository

import kotlinx.coroutines.flow.Flow

interface  SocketRepository {
    suspend fun connectSocket()
    suspend fun subscribeSocketResponse() : Flow<String>
    suspend fun send(message : String)
    suspend fun disconnectSocket()
}
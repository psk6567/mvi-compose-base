package kr.co.psk.data.mapper

import kotlinx.coroutines.flow.Flow
import kr.co.psk.data.datasource.socket.SocketDataSource
import kr.co.psk.data.repository.SocketRepository
import javax.inject.Inject

class SocketMapper @Inject constructor(
    private val socketDataSource: SocketDataSource
) : SocketRepository {
    override suspend fun connectSocket() {
        socketDataSource.connectSocket()
    }

    override suspend fun subscribeSocketResponse(): Flow<String> = socketDataSource.subscribeSocketResponse()

    override suspend fun send(message : String) {
        socketDataSource.sendMessage(message)
    }

    override suspend fun disconnectSocket() {
        socketDataSource.disconnectSocket()
    }


}
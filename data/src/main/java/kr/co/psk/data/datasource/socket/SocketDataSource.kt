package kr.co.psk.data.datasource.socket

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kr.co.psk.common.BuildConfig
import kr.co.psk.common.di.ApplicationIoScope
import java.io.EOFException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.ServerSocket
import java.net.Socket
import javax.inject.Inject

class SocketDataSource @Inject constructor(
    private val ioScope: CoroutineScope,
) {
    private lateinit var socket: Socket
    private lateinit var socketJob: Job
    private var isConnected = false
    private lateinit var socketChannel: Channel<String>
    fun connectSocket() {
        socketChannel = Channel(
            capacity = 50,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )
        socketJob = ioScope.launch {
            isConnected = true
            socket = Socket("localhost", BuildConfig.SOCKET_PORT)
            while (isConnected) {
                try {
                    val inputStream = ObjectInputStream(socket.getInputStream())
                    val response = inputStream.readObject().toString()
                    socketChannel.trySend(response)
                } catch (e:EOFException) {}
            }
        }
    }

    suspend fun subscribeSocketResponse() = socketChannel.receiveAsFlow()


    fun sendMessage(message: String) {
        ioScope.launch {
            val outputStream = ObjectOutputStream(socket.getOutputStream())
            outputStream.writeObject(message)
            outputStream.flush()
        }
    }

    suspend fun disconnectSocket() {
        socketChannel.close()
        isConnected = false
        socket.close()
        socketJob.cancel()
    }
}
package kr.co.psk.mvi_compose.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kr.co.psk.common.BuildConfig
import kr.co.psk.common.di.ApplicationIoScope
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.ServerSocket
import java.net.Socket
import java.net.SocketException
import javax.inject.Inject

@AndroidEntryPoint
class SocketServerService : Service() {

    @Inject
    @ApplicationIoScope
    lateinit var ioScope: CoroutineScope

    //SocketServer job
    private lateinit var socketServerJob : Job

    private var isRunningSocketServer = true

    //socketServer
    private lateinit var socketServer : ServerSocket
    private lateinit var client : Socket

    inner class ServiceBinder : Binder() {
        fun getService() : SocketServerService = this@SocketServerService
    }
    private val binder = ServiceBinder()

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    internal fun startSocketServer() {
        isRunningSocketServer = true
        socketServer = ServerSocket(BuildConfig.SOCKET_PORT)
        socketServerJob = ioScope.launch {
            client = socketServer.accept()
            while (isRunningSocketServer) {
                try {
//                    val clientHost = client.localAddress
//                    val clientPort = client.port
                    val inputStream = ObjectInputStream(client.getInputStream())
                    val readMessage = inputStream.readObject()
                    val returnMessage = "echo $readMessage"
                    val outputStream = ObjectOutputStream(client.getOutputStream())
                    outputStream.writeObject(returnMessage)
                    outputStream.flush()
                    //socket.close()
                } catch(e : SocketException) { }
            }
        }
    }

    internal fun stopSocketServer() {
        isRunningSocketServer = false
        socketServer.close()
        socketServerJob.cancel()
    }
}
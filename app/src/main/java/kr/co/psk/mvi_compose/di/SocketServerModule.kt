package kr.co.psk.mvi_compose.di

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kr.co.psk.mvi_compose.service.SocketServerService
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class SocketServerModule @Inject constructor(
    @ApplicationContext private val context : Context
) {
    private lateinit var service: SocketServerService
    private val serviceConnection : ServiceConnection by lazy{
        object : ServiceConnection {
            override fun onServiceConnected(
                name: ComponentName,
                binder: IBinder,
            ) {
                val mb = binder as SocketServerService.ServiceBinder
                service = mb.getService()
            }
            override fun onServiceDisconnected(name: ComponentName) {
                service.stopSelf()
            }
        }
    }

    init {
        bindService()
    }

    internal fun startSocketServer() {
        service.startSocketServer()
    }

    internal fun stopSocketServer() {
        service.stopSocketServer()
    }

    internal fun stopService() {
        if (::service.isInitialized)
            context.unbindService(serviceConnection)
    }

    private fun bindService() {
        val intent = Intent(context, SocketServerService::class.java)
        context.bindService(
            intent,
            serviceConnection,
            Context.BIND_AUTO_CREATE
        )
    }
}
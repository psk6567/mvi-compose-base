package kr.co.psk.mvi_compose.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kr.co.psk.mvi_compose.base.BaseActivity
import kr.co.psk.mvi_compose.base.BaseScreen
import kr.co.psk.mvi_compose.di.SocketServerModule
import kr.co.psk.mvi_compose.ui.example.ExampleScreen
import javax.inject.Inject

@AndroidEntryPoint
class ExampleActivity : BaseActivity() {
    @Inject
    lateinit var socketServerModule: SocketServerModule
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaseScreen {
                ExampleScreen()
            }
        }
    }

    override fun onDestroy() {
        socketServerModule.stopService()
        super.onDestroy()
    }
}
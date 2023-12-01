package kr.co.psk.mvi_compose.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kr.co.psk.mvi_compose.base.BaseActivity
import kr.co.psk.mvi_compose.base.BaseScreen
import kr.co.psk.mvi_compose.ui.example.ExampleScreen

@AndroidEntryPoint
class ExampleActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaseScreen {
                ExampleScreen()
            }
        }
    }
}
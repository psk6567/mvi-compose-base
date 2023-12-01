package kr.co.psk.mvi_compose.base

import android.os.Bundle
import androidx.activity.ComponentActivity

open class BaseActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
package kr.co.psk.mvi_compose.ui.example.socket

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import kr.co.psk.mvi_compose.common.OnLifecycleEvent

@Composable
internal fun ExampleSocketScreen(viewModel : ExampleSocketViewModel) {

    OnLifecycleEvent(viewModel)

    Text(text = "Socket")
}
package kr.co.psk.mvi_compose.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import kr.co.psk.mvi_compose.base.BaseViewModel

@Composable
fun OnLifecycleEvent(viewModel : BaseViewModel) {
    val lifecycleOwner = rememberUpdatedState(LocalLifecycleOwner.current)
    DisposableEffect(lifecycleOwner.value) {
        val lifecycle = lifecycleOwner.value.lifecycle
        val observer = LifecycleEventObserver { owner, event ->
            when(event) {
                Lifecycle.Event.ON_RESUME -> {
                    viewModel.onResume()
                }
                Lifecycle.Event.ON_PAUSE -> {
                    viewModel.onPause()
                }
                else -> {}
            }
        }

        lifecycle.addObserver(observer)
        onDispose {
            lifecycle.removeObserver(observer)
        }
    }
}
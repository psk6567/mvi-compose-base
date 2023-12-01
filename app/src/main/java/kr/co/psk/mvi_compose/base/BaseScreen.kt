package kr.co.psk.mvi_compose.base

import androidx.compose.runtime.Composable
import kr.co.psk.mvi_compose.ui.theme.MVIComposeTheme

@Composable
fun BaseScreen(contents: @Composable () -> Unit) {
    MVIComposeTheme {
        contents()
    }
}
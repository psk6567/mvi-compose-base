package kr.co.psk.mvi_compose.ui.example.socket

import kr.co.psk.mvi_compose.ui.example.retrofit2.ExampleRetrofit2SideEffect

sealed class ExampleSocketSideEffect {
    data class ShowToast(val message : String) : ExampleSocketSideEffect()
}

package kr.co.psk.mvi_compose.ui.example.socket

sealed class ExampleSocketSideEffect {
    data class ShowToast(val message : String) : ExampleSocketSideEffect()
}

package kr.co.psk.mvi_compose.ui.example.retrofit2

sealed class ExampleRetrofit2SideEffect {
    data class ErrorToast(val message : String) : ExampleRetrofit2SideEffect()
}
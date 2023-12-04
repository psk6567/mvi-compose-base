package kr.co.psk.mvi_compose.ui.example.room

import kr.co.psk.mvi_compose.ui.example.retrofit2.ExampleRetrofit2SideEffect

sealed class ExampleRoomSideEffect {
    data class ShowToast(val message : String) : ExampleRoomSideEffect()
}

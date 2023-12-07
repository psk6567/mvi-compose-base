package kr.co.psk.mvi_compose.ui.example.datastore

import kr.co.psk.mvi_compose.ui.example.retrofit2.ExampleRetrofit2SideEffect

sealed class ExampleDataStoreSideEffect {
    data class ShowToast(val message : String) : ExampleDataStoreSideEffect()
}

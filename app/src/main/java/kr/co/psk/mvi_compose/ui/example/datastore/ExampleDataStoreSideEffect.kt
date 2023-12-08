package kr.co.psk.mvi_compose.ui.example.datastore

sealed class ExampleDataStoreSideEffect {
    data class ShowToast(val message : String) : ExampleDataStoreSideEffect()
}

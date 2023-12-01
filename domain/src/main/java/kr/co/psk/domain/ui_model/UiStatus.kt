package kr.co.psk.domain.ui_model

sealed class UiStatus {
    object Loading : UiStatus()
    object IDLE : UiStatus()
    data class Failed(val message: String = "") : UiStatus()
}
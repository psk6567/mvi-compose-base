package kr.co.psk.mvi_compose.ui.example.socket

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kr.co.psk.domain.ui_model.UiStatus

data class ExampleSocketState(
    val status : UiStatus = UiStatus.IDLE,
    var isConnectedSocket : Boolean = false,
)

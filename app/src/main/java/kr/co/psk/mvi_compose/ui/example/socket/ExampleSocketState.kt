package kr.co.psk.mvi_compose.ui.example.socket

import kr.co.psk.domain.ui_model.RoomTestSampleListUiModel
import kr.co.psk.domain.ui_model.RoomTestUiModel
import kr.co.psk.domain.ui_model.UiStatus

data class ExampleSocketState(
    val status : UiStatus = UiStatus.IDLE
)

package kr.co.psk.mvi_compose.ui.example.room

import kr.co.psk.domain.ui_model.RoomTestSampleListUiModel
import kr.co.psk.domain.ui_model.RoomTestUiModel
import kr.co.psk.domain.ui_model.UiStatus

data class ExampleRoomState(
    val status : UiStatus = UiStatus.IDLE,
    val uiModel : RoomTestUiModel = RoomTestUiModel()
)

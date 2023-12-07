package kr.co.psk.mvi_compose.ui.example.datastore

import kr.co.psk.domain.ui_model.RoomTestSampleListUiModel
import kr.co.psk.domain.ui_model.RoomTestUiModel
import kr.co.psk.domain.ui_model.UiStatus

data class ExampleDataStoreState(
    val status : UiStatus = UiStatus.IDLE,
    val testString : String = "",
)

package kr.co.psk.mvi_compose.ui.example.retrofit2

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kr.co.psk.domain.ui_model.Retrofit2TestUiModel
import kr.co.psk.domain.ui_model.UiStatus

data class ExampleRetrofit2State (
    val status : UiStatus = UiStatus.IDLE,
    val list : ImmutableList<Retrofit2TestUiModel> = persistentListOf()
)

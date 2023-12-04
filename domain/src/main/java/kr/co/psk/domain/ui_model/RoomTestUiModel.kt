package kr.co.psk.domain.ui_model

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kr.co.psk.data.model.entity.RoomTestEntity

data class RoomTestUiModel (
    val count : String = "0",
    val sampleList : ImmutableList<RoomTestSampleListUiModel> = persistentListOf()
)

data class RoomTestSampleListUiModel(
    val idx : String,
    val id : String,
    val userName : String,
    val age : String
)
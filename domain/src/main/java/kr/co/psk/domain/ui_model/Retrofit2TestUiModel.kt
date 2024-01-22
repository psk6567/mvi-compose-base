package kr.co.psk.domain.ui_model

import androidx.annotation.Keep
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Immutable
@Keep
data class Retrofit2TestUiModel (
    val index : String,
    val title : String,
    val body : String
)

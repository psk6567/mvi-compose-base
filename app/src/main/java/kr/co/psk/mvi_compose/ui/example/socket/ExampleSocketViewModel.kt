package kr.co.psk.mvi_compose.ui.example.socket

import android.content.Context
import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kr.co.psk.common.model.onFailed
import kr.co.psk.common.model.onLoading
import kr.co.psk.common.model.onSuccess
import kr.co.psk.domain.ui_model.UiStatus
import kr.co.psk.domain.usecase.room.RoomDeleteUseCase
import kr.co.psk.domain.usecase.room.RoomInsertUseCase
import kr.co.psk.domain.usecase.room.RoomSelectUseCase
import kr.co.psk.mvi_compose.base.BaseViewModel
import kr.co.psk.mvi_compose.ui.example.room.ExampleRoomSideEffect
import kr.co.psk.mvi_compose.ui.example.room.ExampleRoomState
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.security.SecureRandom
import javax.inject.Inject

@HiltViewModel
class ExampleSocketViewModel @Inject constructor(
    @ApplicationContext context: Context,
) : BaseViewModel(context), ContainerHost<ExampleSocketState, ExampleSocketSideEffect> {
    override val container = container<ExampleSocketState, ExampleSocketSideEffect>(
        ExampleSocketState()
    )

    override fun onResume() {
        super.onResume()
        Log.e("Test","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("Test","onPause")
    }
}
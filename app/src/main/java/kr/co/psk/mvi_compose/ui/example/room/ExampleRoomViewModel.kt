package kr.co.psk.mvi_compose.ui.example.room

import android.content.Context
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
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.security.SecureRandom
import javax.inject.Inject

@HiltViewModel
class ExampleRoomViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val roomInsertUseCase: RoomInsertUseCase,
    private val roomSelectUseCase: RoomSelectUseCase,
    private val roomDeleteUseCase: RoomDeleteUseCase,
) : BaseViewModel(context), ContainerHost<ExampleRoomState, ExampleRoomSideEffect> {

    private var testCount = 0

    override val container = container<ExampleRoomState, ExampleRoomSideEffect>(
        ExampleRoomState()
    )

    init {
        selectTest()
    }

    fun insertTest() {
        ioScope {
            intent {
                roomInsertUseCase(
                    id = "TestID_$testCount",
                    userName = "TestName_$testCount",
                    age = SecureRandom().nextInt(100)
                ).collect { response ->
                    response
                        .onLoading {
                            reduce {
                                state.copy(status = UiStatus.Loading)
                            }
                        }
                        .onSuccess {
                            testCount++
                            selectTest()
                        }
                        .onFailed { e ->
                            postSideEffect(ExampleRoomSideEffect.ShowToast(e.toString()))
                        }
                }
            }
        }
    }

    fun selectTest() {
        ioScope {
            intent {
                roomSelectUseCase().collect{ response ->
                    response
                        .onLoading {
                            reduce {
                                state.copy(status = UiStatus.Loading)
                            }
                        }
                        .onSuccess { data ->
                            reduce {
                                state.copy(status = UiStatus.IDLE, uiModel = data)
                            }
                        }
                        .onFailed { e ->
                            postSideEffect(ExampleRoomSideEffect.ShowToast(e.toString()))
                        }
                }
            }
        }
    }

    fun deleteTest() {
        ioScope {
            intent {
                roomDeleteUseCase().collect{ response ->
                    response
                        .onLoading {
                            reduce {
                                state.copy(status = UiStatus.Loading)
                            }
                        }
                        .onSuccess {
                            selectTest()
                            testCount = 0
                        }
                        .onFailed { e ->
                            postSideEffect(ExampleRoomSideEffect.ShowToast(e.toString()))
                        }
                }
            }
        }
    }

}
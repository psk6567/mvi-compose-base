package kr.co.psk.mvi_compose.ui.example.datastore

import android.content.Context
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.collect
import kr.co.psk.common.model.onFailed
import kr.co.psk.common.model.onLoading
import kr.co.psk.common.model.onSuccess
import kr.co.psk.domain.ui_model.UiStatus
import kr.co.psk.domain.usecase.datastore.GetPreferencesTestUseCase
import kr.co.psk.domain.usecase.datastore.SetPreferencesTestUseCase
import kr.co.psk.domain.usecase.room.RoomDeleteUseCase
import kr.co.psk.domain.usecase.room.RoomInsertUseCase
import kr.co.psk.domain.usecase.room.RoomSelectUseCase
import kr.co.psk.mvi_compose.base.BaseViewModel
import kr.co.psk.mvi_compose.ui.example.room.ExampleRoomSideEffect
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.security.SecureRandom
import javax.inject.Inject

@HiltViewModel
class ExampleDataStoreViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val getPreferencesTestUseCase: GetPreferencesTestUseCase,
    private val setPreferencesTestUseCase: SetPreferencesTestUseCase
) : BaseViewModel(context), ContainerHost<ExampleDataStoreState, ExampleDataStoreSideEffect> {


    override val container = container<ExampleDataStoreState, ExampleDataStoreSideEffect>(
        ExampleDataStoreState()
    )

    init {
        getPreferencesTestString()
    }

    fun getPreferencesTestString() {
        ioScope {
            intent {
                getPreferencesTestUseCase().collect { response ->
                    response
                        .onLoading {
                            reduce {
                                    state.copy(status = UiStatus.Loading)
                                }
                        }
                        .onSuccess { data ->
                            reduce {
                                state.copy(testString = data)
                            }
                        }
                        .onFailed {e->
                            postSideEffect(ExampleDataStoreSideEffect.ShowToast(e.toString()))
                        }
                }
            }
        }
    }

    fun setPreferencesTestString(testString : String) {
        ioScope {
            setPreferencesTestUseCase(testString).collect { response ->
                response.onSuccess {
                    getPreferencesTestString()
                }
            }

        }
    }


//    fun insertTest() {
//        ioScope {
//            intent {
//                roomInsertUseCase(
//                    id = "TestID_$testCount",
//                    userName = "TestName_$testCount",
//                    age = SecureRandom().nextInt(100)
//                ).collect { response ->
//                    response
//                        .onLoading {
//                            reduce {
//                                state.copy(status = UiStatus.Loading)
//                            }
//                        }
//                        .onSuccess {
//                            testCount++
//                            selectTest()
//                        }
//                        .onFailed { e ->
//                            postSideEffect(ExampleRoomSideEffect.ShowToast(e.toString()))
//                        }
//                }
//            }
//        }
//    }

}
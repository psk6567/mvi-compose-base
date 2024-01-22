package kr.co.psk.mvi_compose.ui.example.retrofit2

import android.content.Context
import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kr.co.psk.common.model.onFailed
import kr.co.psk.common.model.onLoading
import kr.co.psk.common.model.onSuccess
import kr.co.psk.domain.ui_model.Retrofit2TestUiModel
import kr.co.psk.domain.ui_model.UiStatus
import kr.co.psk.domain.usecase.retrofit2.GetRetrofit2TestUseCase
import kr.co.psk.mvi_compose.base.BaseViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class ExampleRetrofit2ViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val getRetrofit2TestUseCase: GetRetrofit2TestUseCase,
) : ContainerHost<ExampleRetrofit2State, ExampleRetrofit2SideEffect>, BaseViewModel(context) {
    override val container = container<ExampleRetrofit2State, ExampleRetrofit2SideEffect>(
        ExampleRetrofit2State()
    )

    private var _recompositionTestList = MutableStateFlow(persistentListOf<Retrofit2TestUiModel>())
    val recompositionTestList get() = _recompositionTestList.asStateFlow()

    fun getExampleData() {
        ioScope {
            intent {
                getRetrofit2TestUseCase().collect { response ->
                    response.onSuccess { data ->
                        _recompositionTestList.update { data.toPersistentList() }
                        reduce {
                            state.copy(
                                status = UiStatus.IDLE,
                                list = data
                            )
                        }
                    }
                    .onLoading {
                        reduce {
                            state.copy(status = UiStatus.Loading)
                        }
                    }
                    .onFailed { e ->
                        e.printStackTrace()
                        postSideEffect(ExampleRetrofit2SideEffect.ErrorToast(e.toString()))
                    }
                }
            }
        }
    }

    fun testSideEffect() {
        intent {
            postSideEffect(ExampleRetrofit2SideEffect.ErrorToast("Side Effect!!!"))
        }
    }

    fun testRecomposition() {
/*        intent {
            reduce {
                val origin = state.list.toMutableList()
                origin[1]  = origin[1].copy(body = origin[1].body+"~")
                state.copy(
                    list = origin.toImmutableList()
                )
            }
        }*/
        _recompositionTestList.update { origin ->
            Log.e("Test origin01",origin[1].index)
            origin.set(1,origin[1].copy(body = origin[1].body+"~"))
        }
    }
}
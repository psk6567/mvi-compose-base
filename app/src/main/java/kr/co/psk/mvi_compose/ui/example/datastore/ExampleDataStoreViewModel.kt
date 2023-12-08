package kr.co.psk.mvi_compose.ui.example.datastore

import android.content.Context
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kr.co.psk.common.model.onFailed
import kr.co.psk.common.model.onLoading
import kr.co.psk.common.model.onSuccess
import kr.co.psk.domain.ui_model.UiStatus
import kr.co.psk.domain.usecase.datastore.GetPreferencesTestUseCase
import kr.co.psk.domain.usecase.datastore.SetPreferencesTestUseCase
import kr.co.psk.mvi_compose.base.BaseViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
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

}
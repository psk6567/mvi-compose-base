package kr.co.psk.mvi_compose.ui.example.socket

import android.content.Context
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kr.co.psk.common.model.onFailed
import kr.co.psk.common.model.onLoading
import kr.co.psk.common.model.onSuccess
import kr.co.psk.domain.ui_model.UiStatus
import kr.co.psk.domain.usecase.socket.ConnectSocketUseCase
import kr.co.psk.domain.usecase.socket.SendMessageUseCase
import kr.co.psk.domain.usecase.socket.SubscribeResponseUseCase
import kr.co.psk.mvi_compose.base.BaseViewModel
import kr.co.psk.mvi_compose.di.SocketServerModule
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject


@HiltViewModel
class ExampleSocketViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val socketServerModule: SocketServerModule,
    private val connectSocketUseCase: ConnectSocketUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
    private val subscribeResponseUseCase: SubscribeResponseUseCase,
) : BaseViewModel(context), ContainerHost<ExampleSocketState, ExampleSocketSideEffect> {

    val _messageList = MutableStateFlow<PersistentList<String>>(persistentListOf())
    val messageList: StateFlow<ImmutableList<String>> = _messageList.asStateFlow()

    override val container = container<ExampleSocketState, ExampleSocketSideEffect>(
        ExampleSocketState()
    )

    override fun onResume() {
        super.onResume()
        socketServerModule.startSocketServer()
        connectSocket()
    }

    override fun onPause() {
        super.onPause()
        socketServerModule.stopSocketServer()
    }

    private fun connectSocket() {
        ioScope {
            intent {
                connectSocketUseCase().collect { response ->
                    response
                        .onLoading {
                            reduce {
                                state.copy(status = UiStatus.Loading)
                            }
                        }
                        .onSuccess { data ->
                            reduce {
                                state.copy(status = UiStatus.IDLE, isConnectedSocket = true)
                            }
                            subscribeResponse()
                        }
                        .onFailed { e ->
                            postSideEffect(ExampleSocketSideEffect.ShowToast(e.toString()))
                        }
                }
            }
        }
    }

    private fun subscribeResponse() {
        ioScope {
            subscribeResponseUseCase().collect { response ->
                _messageList.update {originList ->
                    originList.add(0,response)
                }
            }
        }
    }


    fun sendMessage(message: String) {
        if (message.isNotEmpty()) {
            ioScope {
                sendMessageUseCase(message)
            }
        }
    }

}
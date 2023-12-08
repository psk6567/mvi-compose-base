package kr.co.psk.domain.usecase.socket

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kr.co.psk.common.model.ResponseState
import kr.co.psk.data.repository.SocketRepository
import javax.inject.Inject

class ConnectSocketUseCase @Inject constructor(
    private val socketRepository: SocketRepository
) {
    suspend operator fun invoke() = flow<ResponseState<Unit>> {
        emit(ResponseState.Loading)
        socketRepository.connectSocket()
        emit(ResponseState.Success(Unit))
    }.catch {e ->
        emit(ResponseState.Failure(e))
    }


}
package kr.co.psk.domain.usecase.room

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kr.co.psk.common.model.ResponseState
import kr.co.psk.data.repository.RoomRepository
import javax.inject.Inject

class RoomDeleteUseCase @Inject constructor(
    private val roomRepository: RoomRepository
) {
    suspend operator fun invoke() = flow<ResponseState<Unit>> {
        emit(ResponseState.Loading)
        roomRepository.delete()
        emit(ResponseState.Success(Unit))
    }.catch {e ->
        emit(ResponseState.Failure(e))
    }
}
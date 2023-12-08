package kr.co.psk.domain.usecase.room

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kr.co.psk.common.model.ResponseState
import kr.co.psk.data.model.entity.RoomTestEntity
import kr.co.psk.data.repository.RoomRepository
import javax.inject.Inject

class RoomInsertUseCase @Inject constructor(
    private val roomRepository: RoomRepository
) {
    suspend operator fun invoke(id : String, userName : String, age : Int) = flow<ResponseState<Unit>> {
        emit(ResponseState.Loading)
        roomRepository.insert(
            RoomTestEntity(
                id = id,
                userName = userName,
                age = age
            )
        )
        emit(ResponseState.Success(Unit))
    }.catch {e ->
        emit(ResponseState.Failure(e))
    }
}
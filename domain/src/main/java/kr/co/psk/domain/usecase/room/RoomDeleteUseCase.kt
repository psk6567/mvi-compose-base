package kr.co.psk.domain.usecase.room

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kr.co.psk.common.model.ResponseState
import kr.co.psk.data.model.entity.RoomTestEntity
import kr.co.psk.data.repository.Retrofit2Repository
import kr.co.psk.data.repository.RoomRepository
import kr.co.psk.domain.ui_model.Retrofit2TestUiModel
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
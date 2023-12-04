package kr.co.psk.domain.usecase.room

import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kr.co.psk.common.model.ResponseState
import kr.co.psk.data.repository.RoomRepository
import kr.co.psk.domain.ui_model.RoomTestSampleListUiModel
import kr.co.psk.domain.ui_model.RoomTestUiModel
import javax.inject.Inject

class RoomSelectUseCase @Inject constructor(
    private val roomRepository: RoomRepository,
) {
    suspend operator fun invoke() = flow<ResponseState<RoomTestUiModel>> {
        emit(ResponseState.Loading)
        emit(
            ResponseState.Success(
                with(roomRepository.select()) {
                    RoomTestUiModel(
                        count = size.toString(),
                        sampleList = map { item ->
                            RoomTestSampleListUiModel(
                                idx = item.idx.toString(),
                                id = item.id,
                                userName = item.userName,
                                age = item.age.toString()
                            )
                        }.toImmutableList()
                    )
                }
            )
        )
    }.catch { e ->
        emit(ResponseState.Failure(e))
    }
}
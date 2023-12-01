package kr.co.psk.domain.usecase

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kr.co.psk.common.model.ResponseState
import kr.co.psk.data.repository.Retrofit2Repository
import kr.co.psk.domain.ui_model.Retrofit2TestUiModel
import javax.inject.Inject

class GetRetrofit2TestUseCase @Inject constructor(
    private val retrofit2Repository: Retrofit2Repository,
) {
    suspend operator fun invoke() = flow<ResponseState<ImmutableList<Retrofit2TestUiModel>>> {
        emit(ResponseState.Loading)
        emit(
            ResponseState.Success(
                retrofit2Repository.getRetrofit2Test().mapIndexed() { index,dto ->
                    Retrofit2TestUiModel(
                        index = index.toString(),
                        title = dto.title,
                        body = dto.body
                    )
                }.toImmutableList()
            )
        )
    }.catch {e ->
        emit(ResponseState.Failure(e))
    }
}
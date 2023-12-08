package kr.co.psk.domain.usecase.datastore

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kr.co.psk.common.model.ResponseState
import kr.co.psk.data.repository.DataStoreRepository
import javax.inject.Inject

class SetPreferencesTestUseCase @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) {
    suspend operator fun invoke(testString : String) = flow<ResponseState<Unit>> {
        emit(ResponseState.Loading)
        dataStoreRepository.setTestPreferences(testString)
        emit(ResponseState.Success(Unit))
    }.catch {e ->
        emit(ResponseState.Failure(e))
    }
}
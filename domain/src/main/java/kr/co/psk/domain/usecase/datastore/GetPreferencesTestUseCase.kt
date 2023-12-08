package kr.co.psk.domain.usecase.datastore

import kotlinx.coroutines.flow.map
import kr.co.psk.common.model.ResponseState
import kr.co.psk.data.repository.DataStoreRepository
import javax.inject.Inject

class GetPreferencesTestUseCase @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) {
    suspend operator fun invoke() = dataStoreRepository.getTestPreferences()
        .map {
            ResponseState.Success(it)
        }
}
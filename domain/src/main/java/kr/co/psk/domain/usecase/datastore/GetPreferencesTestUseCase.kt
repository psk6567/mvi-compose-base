package kr.co.psk.domain.usecase.datastore

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kr.co.psk.common.model.ResponseState
import kr.co.psk.data.repository.DataStoreRepository
import kr.co.psk.data.repository.Retrofit2Repository
import kr.co.psk.domain.ui_model.Retrofit2TestUiModel
import javax.inject.Inject

class GetPreferencesTestUseCase @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) {
    suspend operator fun invoke() = dataStoreRepository.getTestPreferences()
        .map {
            ResponseState.Success(it)
        }
}
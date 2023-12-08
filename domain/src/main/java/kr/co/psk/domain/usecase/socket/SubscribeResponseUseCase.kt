package kr.co.psk.domain.usecase.socket

import kr.co.psk.data.repository.SocketRepository
import javax.inject.Inject

class SubscribeResponseUseCase @Inject constructor(
    private val socketRepository: SocketRepository
) {
    suspend operator fun invoke() = socketRepository.subscribeSocketResponse()
}
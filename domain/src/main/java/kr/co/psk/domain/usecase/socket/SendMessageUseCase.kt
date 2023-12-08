package kr.co.psk.domain.usecase.socket

import kr.co.psk.data.repository.SocketRepository
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val socketRepository: SocketRepository
) {
    suspend operator fun invoke(message : String) = socketRepository.send(message)
}
package com.smialko.reminderhabitstracker.domain.usecases.userUsecases

import com.smialko.reminderhabitstracker.domain.repository.UserRepository
import javax.inject.Inject

class GetUserDetailsUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    operator fun invoke(userId: String) = userRepository.getUserDetails(userId = userId)
}
package com.smialko.reminderhabitstracker.domain.usecases.userUsecases

import com.smialko.reminderhabitstracker.domain.repository.UserRepository
import javax.inject.Inject

class SetUserDetailsUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(userId: String, firstName: String, secondName: String) =
        userRepository.setUserDatails(userId, firstName, secondName)
}
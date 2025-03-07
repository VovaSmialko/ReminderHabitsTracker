package com.smialko.reminderhabitstracker.domain.usecases.firebaseUsecases

import com.smialko.reminderhabitstracker.domain.repository.AuthenticationRepository
import javax.inject.Inject

class FirebaseSignUpUseCase @Inject constructor(
    private val repository: AuthenticationRepository
) {

    operator fun invoke(email: String, password: String, fullName: String) =
        repository.firebaseSignUp(email, password, fullName)
}
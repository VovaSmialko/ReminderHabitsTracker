package com.smialko.reminderhabitstracker.domain.usecases.firebaseUsecases

import com.smialko.reminderhabitstracker.domain.repository.AuthenticationRepository
import com.smialko.reminderhabitstracker.utils.registerValidation.validateEmail
import com.smialko.reminderhabitstracker.utils.registerValidation.validatePassword
import javax.inject.Inject

class FirebaseSignInUseCase @Inject constructor(
    private val repository: AuthenticationRepository
) {

    operator fun invoke(email: String, password: String) =
        repository.firebaseSignIn(email, password)

}
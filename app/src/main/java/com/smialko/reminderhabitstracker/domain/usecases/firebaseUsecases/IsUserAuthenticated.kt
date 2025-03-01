package com.smialko.reminderhabitstracker.domain.usecases.firebaseUsecases

import com.smialko.reminderhabitstracker.domain.repository.AuthenticationRepository
import javax.inject.Inject

class IsUserAuthenticated @Inject constructor(
    private val repository: AuthenticationRepository
) {

    operator fun invoke() = repository.isUserAuthenticatedInFirebase()
}
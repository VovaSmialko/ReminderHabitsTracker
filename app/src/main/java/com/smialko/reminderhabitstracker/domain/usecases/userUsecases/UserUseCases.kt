package com.smialko.reminderhabitstracker.domain.usecases.userUsecases

data class UserUseCases(
    val getUserDetails: GetUserDetailsUseCase,
    val setUserDetailsUseCase: SetUserDetailsUseCase
)

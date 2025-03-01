package com.smialko.reminderhabitstracker.domain.usecases.firebaseUsecases

data class AuthenticationUseCases(
    val isUserAuthenticated: IsUserAuthenticated,
    val firebaseAuthState: FirebaseAuthStateUseCase,
    val firebaseSignInUseCase: FirebaseSignInUseCase,
    val firebaseSignUpUseCase: FirebaseSignUpUseCase,
    val firebaseSignOutUseCase: FirebaseSignOutUseCase
)

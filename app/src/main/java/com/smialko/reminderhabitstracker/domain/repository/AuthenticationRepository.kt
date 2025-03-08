package com.smialko.reminderhabitstracker.domain.repository

import com.smialko.reminderhabitstracker.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface AuthenticationRepository {

    fun isUserAuthenticatedInFirebase(): Boolean

    fun getFirebaseAuthState(): Flow<Boolean>

    fun firebaseSignIn(email: String, password: String): Flow<Response<Boolean>>

    fun firebaseSignOut(): Flow<Response<Boolean>>

    fun firebaseSignUp(
        email: String,
        password: String,
        fullName: String
    ): Flow<Response<Boolean>>

    fun firebaseResetPassword(
        email: String
    ): Flow<Response<Boolean>>

}
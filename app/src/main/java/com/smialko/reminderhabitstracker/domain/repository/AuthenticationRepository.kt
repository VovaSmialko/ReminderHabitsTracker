package com.smialko.reminderhabitstracker.domain.repository

import com.smialko.reminderhabitstracker.utils.Response
import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {

    fun isUserAuthenticatedInFirebase(): Boolean

    fun getFirebaseAuthState(): Flow<Boolean>

    fun firebaseSignIn(email: String, password: String): Flow<Response<Boolean>>

    fun firebaseSignOut(): Flow<Response<Boolean>>

    fun firebaseSignUp(
        email: String,
        password: String,
        firstName: String,
        lastName: String
    ): Flow<Response<Boolean>>

}
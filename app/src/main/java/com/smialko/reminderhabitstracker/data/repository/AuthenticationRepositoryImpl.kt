package com.smialko.reminderhabitstracker.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.smialko.reminderhabitstracker.data.model.user.User
import com.smialko.reminderhabitstracker.domain.repository.AuthenticationRepository
import com.smialko.reminderhabitstracker.utils.Constants.USER_COLLECTION
import com.smialko.reminderhabitstracker.utils.Response
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
) : AuthenticationRepository {

    override fun isUserAuthenticatedInFirebase(): Boolean {
        return firebaseAuth.currentUser != null
    }

    override fun getFirebaseAuthState(): Flow<Boolean> = callbackFlow {
        val authStateListener = FirebaseAuth.AuthStateListener {
            trySend(firebaseAuth.currentUser == null)
        }
        firebaseAuth.addAuthStateListener(authStateListener)
        awaitClose {
            firebaseAuth.removeAuthStateListener(authStateListener)
        }
    }


    override fun firebaseSignIn(email: String, password: String): Flow<Response<Boolean>> = flow {
        emit(Response.Loading)
        runCatching {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
        }.onSuccess {
            emit(Response.Success(true))
        }.onFailure { e ->
            emit(Response.Error(e.localizedMessage ?: "An Unexpected error"))
        }
    }

    override fun firebaseSignOut(): Flow<Response<Boolean>> = flow {
        emit(Response.Loading)
        firebaseAuth.signOut()
        emit(Response.Success(true))
    }

    override fun firebaseSignUp(
        email: String,
        password: String,
        fullName: String,
    ): Flow<Response<Boolean>> = flow {
        emit(Response.Loading)
        runCatching {
            val authResult = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            val userId = authResult.user?.uid ?: throw Exception("Failed to retrieve user ID")
            val user = User(
                fullName = fullName,
                userId = userId,
                email = email,
                password = password
            )
            saveUserInfo(userId, user)
            emit(Response.Success(true))
        }.onFailure { e ->
            emit(Response.Error(e.localizedMessage ?: "An Unexpected error"))
        }
    }

    override fun firebaseResetPassword(email: String): Flow<Response<Boolean>> = flow {
        emit(Response.Loading)
        runCatching {
            firebaseAuth.sendPasswordResetEmail(email).await()
        }.onSuccess {
            emit(Response.Success(true))
        }.onFailure { e ->
            emit(Response.Error(e.localizedMessage ?: "An Unexpected error"))
        }

    }

    private suspend fun saveUserInfo(
        userId: String,
        user: User
    ) {
        firestore.collection(USER_COLLECTION)
            .document(userId)
            .set(user)
            .await()
    }
}
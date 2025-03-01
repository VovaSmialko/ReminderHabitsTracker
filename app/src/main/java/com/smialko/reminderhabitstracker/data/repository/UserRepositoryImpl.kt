package com.smialko.reminderhabitstracker.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.smialko.reminderhabitstracker.data.model.user.User
import com.smialko.reminderhabitstracker.domain.repository.UserRepository
import com.smialko.reminderhabitstracker.utils.Constants.USER_COLLECTION
import com.smialko.reminderhabitstracker.utils.Response
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
) : UserRepository {

    override fun getUserDetails(userId: String): Flow<Response<User>> = callbackFlow {
        trySend(Response.Loading)
        val snapshotListener = firebaseFirestore.collection(USER_COLLECTION)
            .document(userId)
            .addSnapshotListener { snapshot, error ->
                val response = kotlin.runCatching {
                    snapshot?.toObject(User::class.java)
                        ?.let { Response.Success(it) } ?: Response.Error("User data is null")
                }.getOrElse {
                    Response.Error(error?.message ?: it.localizedMessage ?: "Unknown error")
                }
                trySend(response).isSuccess
            }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override fun setUserDatails(
        userId: String, firstName: String, secondName: String
    ): Flow<Response<Boolean>> = flow {
        emit(Response.Loading)

        val userObj = mapOf(
            "firstName" to firstName,
            "secondName" to secondName
        )
        runCatching {
            firebaseFirestore.collection(USER_COLLECTION)
                .document(userId)
                .update(userObj)
                .await()
        }.onSuccess {
            emit(Response.Success(true))
        }.onFailure {
            emit(Response.Error(it.localizedMessage ?: "An Unexpected Error"))
        }
    }
}
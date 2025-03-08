package com.smialko.reminderhabitstracker.domain.repository

import com.smialko.reminderhabitstracker.data.model.user.User
import com.smialko.reminderhabitstracker.utils.Response
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUserDetails(userId: String): Flow<Response<User>>
    fun setUserDatails(
        userId: String,
        fullName: String,
        email: String
    ): Flow<Response<Boolean>>
}
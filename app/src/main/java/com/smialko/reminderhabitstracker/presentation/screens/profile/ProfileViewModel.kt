package com.smialko.reminderhabitstracker.presentation.screens.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.smialko.reminderhabitstracker.data.model.user.User
import com.smialko.reminderhabitstracker.domain.usecases.firebaseUsecases.AuthenticationUseCases
import com.smialko.reminderhabitstracker.domain.usecases.userUsecases.UserUseCases
import com.smialko.reminderhabitstracker.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authUseCases: AuthenticationUseCases,
    private val firebaseAuth: FirebaseAuth,
    private val userUseCases: UserUseCases
) : ViewModel() {

    private val userId = firebaseAuth.currentUser?.uid
    private val _getUserData = mutableStateOf<Response<User?>>(Response.Loading)
    val getUserData: State<Response<User?>> = _getUserData

    private val _setUserData = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val setUserData: State<Response<Boolean>> = _setUserData

    private val _signInState = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val signInState: State<Response<Boolean>> = _signInState

    private val _signOutState = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val signOutState: State<Response<Boolean>> = _signOutState


    fun signOut() {
        viewModelScope.launch {
            authUseCases.firebaseSignOutUseCase().collect {
                _signOutState.value = it
                if (it == Response.Success(true)) {
                    _signInState.value = Response.Success(false)
                }
            }
        }
    }

    init {
        if (userId != null) {
            viewModelScope.launch {
                userUseCases.getUserDetails(userId).collect {
                    _getUserData.value = it
                }
            }
        }
    }

    fun refreshUserInfo() {
        if (userId != null) {
            _getUserData.value = Response.Loading
        }
    }


    fun setUserInfo(fullName: String, email: String) {
        if (userId != null) {
            viewModelScope.launch {
                userUseCases.setUserDetailsUseCase(
                    userId = userId,
                    fullName = fullName,
                    email = email
                ).collect {
                    _setUserData.value = it
                }
            }
        }
    }
}
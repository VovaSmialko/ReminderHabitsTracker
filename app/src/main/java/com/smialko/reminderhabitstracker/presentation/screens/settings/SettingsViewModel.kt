package com.smialko.reminderhabitstracker.presentation.screens.settings

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.smialko.reminderhabitstracker.data.model.user.User
import com.smialko.reminderhabitstracker.domain.usecases.userUsecases.UserUseCases
import com.smialko.reminderhabitstracker.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val userUseCases: UserUseCases
) : ViewModel() {

    private val userId = firebaseAuth.currentUser?.uid
    private val _getUserData = mutableStateOf<Response<User?>>(Response.Success(null))
    val getUserData: State<Response<User?>> = _getUserData

    private val _setUserData = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val setUserData: State<Response<Boolean>> = _setUserData


    fun getUserInfo() {
        if (userId != null) {
            viewModelScope.launch {
                userUseCases.getUserDetails(userId).collect {
                    _getUserData.value = it
                }
            }
        }
    }

    fun setUserInfo(firstName: String, secondName: String) {
        if (userId != null) {
            viewModelScope.launch {
                userUseCases.setUserDetailsUseCase(
                    userId = userId,
                    firstName = firstName,
                    secondName = secondName
                ).collect {
                    _setUserData.value = it
                }
            }
        }
    }
}
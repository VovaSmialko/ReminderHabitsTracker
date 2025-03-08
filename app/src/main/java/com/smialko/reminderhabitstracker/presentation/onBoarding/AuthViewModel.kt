package com.smialko.reminderhabitstracker.presentation.onBoarding

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.smialko.reminderhabitstracker.domain.usecases.firebaseUsecases.AuthenticationUseCases
import com.smialko.reminderhabitstracker.utils.Response
import com.smialko.reminderhabitstracker.utils.registerValidation.RegisterFieldsState
import com.smialko.reminderhabitstracker.utils.registerValidation.RegisterValidation
import com.smialko.reminderhabitstracker.utils.registerValidation.validateEmail
import com.smialko.reminderhabitstracker.utils.registerValidation.validatePassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCases: AuthenticationUseCases,
    private val firebaseAuth: FirebaseAuth,
    private val db: FirebaseFirestore
) : ViewModel() {

    val isUserAuthenticated get() = authUseCases.isUserAuthenticated()

    private val _signInState = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val signInState: State<Response<Boolean>> = _signInState

    private val _signUpState = MutableStateFlow<Response<Boolean>>(Response.Success(false))
    val signUpState: StateFlow<Response<Boolean>> = _signUpState.asStateFlow()

    private val _sendResetPassword = MutableStateFlow<Response<Boolean>>(Response.Success(false))
    val sendResetPassword: StateFlow<Response<Boolean>> = _sendResetPassword.asStateFlow()

    private val _fireBaseAuthState = mutableStateOf(false)
    val firebaseAuthState: State<Boolean> = _fireBaseAuthState

    private val _validation = Channel<RegisterFieldsState>()
    val validation = _validation.receiveAsFlow()

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            authUseCases.firebaseSignInUseCase(email, password).collect {
                _signInState.value = it
            }
        }
    }

    fun signUp(email: String, password: String, fullName: String) {
        if (checkValidation(email, password)) {
            viewModelScope.launch {
                authUseCases.firebaseSignUpUseCase(email, password, fullName).collect {
                    _signUpState.value = it
                }
            }
        } else {
            val registerFieldsState = RegisterFieldsState(
                validateEmail(email),
                validatePassword(password)
            )
            viewModelScope.launch {
                _validation.send(registerFieldsState)
            }
        }
    }

    fun getFirebaseAuthState() {
        viewModelScope.launch {
            authUseCases.firebaseAuthState().collect {
                _fireBaseAuthState.value = it
            }
        }
    }

    fun resetPassword(email: String) {
        viewModelScope.launch {
            authUseCases.firebaseResetPasswordUseCase(email).collect {
                _sendResetPassword.value = it
            }
        }
    }

    private fun checkValidation(email: String, password: String): Boolean {
        val emailValidation = validateEmail(email)
        val passwordValidation = validatePassword(password)
        val shouldRegister =
            emailValidation is RegisterValidation.Success &&
                    passwordValidation is RegisterValidation.Success

        return shouldRegister
    }
}
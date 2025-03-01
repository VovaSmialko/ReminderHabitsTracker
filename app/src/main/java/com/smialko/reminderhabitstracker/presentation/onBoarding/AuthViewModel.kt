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
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCases: AuthenticationUseCases,
    private val firebaseAuth: FirebaseAuth,
    private val db: FirebaseFirestore
) : ViewModel() {

    val email = mutableStateOf("")

    val password = mutableStateOf("")

    val firstName = mutableStateOf("")

    val secondName = mutableStateOf("")

    val isUserAuthenticated get() = authUseCases.isUserAuthenticated()


    private val _signInState = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val signInState: State<Response<Boolean>> = _signInState

    private val _signUpState = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val signUpState: State<Response<Boolean>> = _signUpState

    private val _signOutState = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val signOutState: State<Response<Boolean>> = _signOutState

    private val _fireBaseAuthState = mutableStateOf(false)
    val firebaseAuthState: State<Boolean> = _fireBaseAuthState

    private val _validation = Channel<RegisterFieldsState>()
    val validation = _validation.receiveAsFlow()

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

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            authUseCases.firebaseSignInUseCase(email, password).collect {
                _signInState.value = it
            }
        }
    }

    fun signUp(email: String, password: String, firstName: String, lastName: String) {
        viewModelScope.launch {
            authUseCases.firebaseSignUpUseCase(email, password, firstName, lastName).collect {
                _signUpState.value = it
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


    fun updateEmail(newEmail: String) {
        email.value = newEmail
    }

    fun updatePassword(newPass: String) {
        password.value = newPass
    }

    fun updateFirstName(newFirstName: String) {
        firstName.value = newFirstName
    }

    fun updateSecondName(newSecondName: String) {
        secondName.value = newSecondName
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
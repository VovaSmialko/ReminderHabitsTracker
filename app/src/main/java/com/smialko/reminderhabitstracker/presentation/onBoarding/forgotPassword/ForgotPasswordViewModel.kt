package com.smialko.reminderhabitstracker.presentation.onBoarding.forgotPassword

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(): ViewModel() {

    val email = mutableStateOf("")

    fun updateEmail(updateEmail: String) {
        email.value = updateEmail
    }
}
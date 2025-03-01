package com.smialko.reminderhabitstracker.utils.registerValidation

import android.util.Patterns

fun validateEmail(email: String): RegisterValidation {
    if (email.isEmpty())
        return RegisterValidation.Failed("Email can't be empty")

    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        return RegisterValidation.Failed("Wrong email format")

    return RegisterValidation.Success
}

fun validatePassword(password: String): RegisterValidation {
    if (password.isEmpty())
        return RegisterValidation.Failed("Password can't be empty")

    if (password.length < 8)
        return RegisterValidation.Failed("Password should contains 8 char")

    return RegisterValidation.Success
}
package com.smialko.reminderhabitstracker.presentation.onBoarding.resetPassword

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.smialko.reminderhabitstracker.R
import com.smialko.reminderhabitstracker.presentation.onBoarding.components.ForgotPasswordHeadingTextComponent
import com.smialko.reminderhabitstracker.presentation.onBoarding.components.ImageComponent
import com.smialko.reminderhabitstracker.presentation.onBoarding.components.ActionButton
import com.smialko.reminderhabitstracker.presentation.onBoarding.components.PasswordInputComponent
import com.smialko.reminderhabitstracker.presentation.onBoarding.components.TextInfoComponent

@Composable
fun ResetPasswordScreen(navController: NavHostController) {
    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Column {
            ImageComponent(image = R.drawable.reset_password)
            Spacer(modifier = Modifier.height(10.dp))
            ForgotPasswordHeadingTextComponent(action = "Reset")
            TextInfoComponent(
                textVal = "Don't worry, strange things happen. Please enter the email address associated with your account."
            )
            Spacer(modifier = Modifier.height(20.dp))
            Column {
                PasswordInputComponent(labelVal = "Password")
                Spacer(modifier = Modifier.height(15.dp))
                PasswordInputComponent(labelVal = "Confirm new password")
            }
            ActionButton(labelVal = "Submit", navController = navController)
        }
    }
}
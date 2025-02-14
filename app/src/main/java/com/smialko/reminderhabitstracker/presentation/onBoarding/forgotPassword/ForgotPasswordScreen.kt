package com.smialko.reminderhabitstracker.presentation.onBoarding.forgotPassword

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.smialko.reminderhabitstracker.R
import com.smialko.reminderhabitstracker.presentation.onBoarding.components.ForgotPasswordHeadingTextComponent
import com.smialko.reminderhabitstracker.presentation.onBoarding.components.ImageComponent
import com.smialko.reminderhabitstracker.presentation.onBoarding.components.ActionButton
import com.smialko.reminderhabitstracker.presentation.onBoarding.components.CustomTextField
import com.smialko.reminderhabitstracker.presentation.onBoarding.components.TextInfoComponent

@Composable
fun ForgotPasswordScreen(navController: NavHostController) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        color = Color.White
    ) {
        Column {
            ImageComponent(image = R.drawable.modern_forgot_password)
            Spacer(modifier = Modifier.height(10.dp))
            ForgotPasswordHeadingTextComponent(action = "Forgot")
            TextInfoComponent(
                textVal = stringResource(R.string.dont_worry)
            )
            Spacer(modifier = Modifier.height(20.dp))
            CustomTextField(labelVal = "Email", icon = R.drawable.at_symbol)
            ActionButton(labelVal = "Submit", navController)
        }
    }
}
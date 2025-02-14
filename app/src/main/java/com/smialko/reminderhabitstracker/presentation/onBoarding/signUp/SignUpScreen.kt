package com.smialko.reminderhabitstracker.presentation.onBoarding.signUp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.smialko.reminderhabitstracker.R
import com.smialko.reminderhabitstracker.presentation.onBoarding.components.BottomSignupTextComponent
import com.smialko.reminderhabitstracker.presentation.onBoarding.components.HeadingTextComponent
import com.smialko.reminderhabitstracker.presentation.onBoarding.components.ImageComponent
import com.smialko.reminderhabitstracker.presentation.onBoarding.components.ActionButton
import com.smialko.reminderhabitstracker.presentation.onBoarding.components.CustomTextField
import com.smialko.reminderhabitstracker.presentation.onBoarding.components.SignupTermsAndPrivacyText

@Composable
fun SignupScreen(navController: NavHostController) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        color = Color.White
    ) {
        Column {
            ImageComponent(image = R.drawable.signup_svg)
            Spacer(modifier = Modifier.height(10.dp))
            HeadingTextComponent(heading = "Sign Up")
            Spacer(modifier = Modifier.height(20.dp))
            Column {
                CustomTextField(labelVal = "Email", icon = R.drawable.at_symbol)
                Spacer(modifier = Modifier.height(15.dp))
                CustomTextField(labelVal = "full name", icon = R.drawable.lockperson)
                Spacer(modifier = Modifier.height(15.dp))
                CustomTextField(labelVal = "mobile", icon = R.drawable.lockphone)
            }
            Spacer(modifier = Modifier.height(20.dp))
            SignupTermsAndPrivacyText()
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomStart
            ) {
                Column {
                    ActionButton(labelVal = "Continue", navController = navController)
                    Spacer(modifier = Modifier.height(10.dp))
                    BottomSignupTextComponent(navController)
                }
            }
        }
    }
}
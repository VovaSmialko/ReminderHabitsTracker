package com.smialko.reminderhabitstracker.presentation.onBoarding.loginScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.smialko.reminderhabitstracker.R
import com.smialko.reminderhabitstracker.presentation.onBoarding.components.BottomComponent
import com.smialko.reminderhabitstracker.presentation.onBoarding.components.BottomLoginTextComponent
import com.smialko.reminderhabitstracker.presentation.onBoarding.components.ForgotPasswordTextComponent
import com.smialko.reminderhabitstracker.presentation.onBoarding.components.HeadingTextComponent
import com.smialko.reminderhabitstracker.presentation.onBoarding.components.ImageComponent
import com.smialko.reminderhabitstracker.presentation.onBoarding.components.CustomTextField
import com.smialko.reminderhabitstracker.presentation.onBoarding.components.PasswordInputComponent
import com.smialko.reminderhabitstracker.ui.theme.ReminderHabitsTrackerTheme

@Composable
fun LoginScreen(navController: NavHostController) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
        ,
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            ImageComponent(image = R.drawable.modern_habits_svg)
            Spacer(modifier = Modifier.height(10.dp))
            HeadingTextComponent(heading = "Login")
            Spacer(modifier = Modifier.height(20.dp))
            Column {
                CustomTextField(labelVal = "Email", R.drawable.at_symbol)
                Spacer(modifier = Modifier.height(15.dp))
                PasswordInputComponent(labelVal = "Password")
                Spacer(modifier = Modifier.height(15.dp))
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ForgotPasswordTextComponent(navController)
                }
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Column {
                        BottomComponent(navController)
                        Spacer(modifier = Modifier.height(12.dp))
                        BottomLoginTextComponent(
                            initialText = "Haven't we seen you around here before? ",
                            action = "Join our coven!",
                            navController
                        )
                    }
                }
            }
        }
    }
}


@Preview(uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun LogPrev() {
    ReminderHabitsTrackerTheme {
        val navController = rememberNavController()
        LoginScreen(navController = navController)
    }
}

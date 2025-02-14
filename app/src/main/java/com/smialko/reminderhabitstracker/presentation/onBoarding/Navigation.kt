package com.smialko.reminderhabitstracker.presentation.onBoarding

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.smialko.reminderhabitstracker.presentation.onBoarding.forgotPassword.ForgotPasswordScreen
import com.smialko.reminderhabitstracker.presentation.onBoarding.loginScreen.LoginScreen
import com.smialko.reminderhabitstracker.presentation.onBoarding.resetPassword.ResetPasswordScreen
import com.smialko.reminderhabitstracker.presentation.onBoarding.signUp.SignupScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "LoginScreen"
    ) {
        composable(
            route = "LoginScreen"
        ) {
            LoginScreen(
                navController
            )
        }
        composable(
            route = "ForgotPassword"
        ) {
            ForgotPasswordScreen(navController)
        }
        composable(route = "ResetPassword") {
            ResetPasswordScreen(navController)
        }
        composable(
            route = "SignupScreen"
        ) {
            SignupScreen(navController)
        }
    }
}

@Composable
fun HalloweenApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Navigation()
    }
}
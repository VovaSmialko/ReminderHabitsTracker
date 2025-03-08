package com.smialko.reminderhabitstracker.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    splashScreeContent: @Composable () -> Unit,
    loginScreenContent: @Composable () -> Unit,
    signUpScreenContent: @Composable () -> Unit,
    forgotPasswordScreenContent: @Composable () -> Unit,
    routineMainScreenContent: @Composable () -> Unit,
    addTaskScreenContent: @Composable (Int) -> Unit,
    habitsScreenContent: @Composable () -> Unit,
    profileScreenContent: @Composable () -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            splashScreeContent()
        }
        composable(Screen.Login.route) {
            loginScreenContent()
        }
        composable(Screen.SingUp.route) {
            signUpScreenContent()
        }
        composable(Screen.ForgotPassword.route) {
            forgotPasswordScreenContent()
        }
        homeScreenNavGraph(
            routineMainScreenContent = routineMainScreenContent,
            addTaskScreenContent = addTaskScreenContent
        )
        composable(Screen.Habits.route) {
            habitsScreenContent()
        }
        composable(Screen.Profile.route) {
            profileScreenContent()
        }
    }
}
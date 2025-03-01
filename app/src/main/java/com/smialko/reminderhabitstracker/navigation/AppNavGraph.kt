package com.smialko.reminderhabitstracker.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.smialko.reminderhabitstracker.domain.entity.ToDoTask

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    splashScreeContent: @Composable () -> Unit,
    loginScreenContent: @Composable () -> Unit,
    signUpScreenContent: @Composable () -> Unit,
    forgotPasswordScreenContent: @Composable () -> Unit,
    resetPasswordScreenContent: @Composable () -> Unit,
    routineMainScreenContent: @Composable () -> Unit,
    addTaskScreenContent: @Composable (Int) -> Unit,
    habitsScreenContent: @Composable () -> Unit,
    settingsScreenContent: @Composable () -> Unit
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
        composable(Screen.ResetPassword.route) {
            resetPasswordScreenContent()
        }
        homeScreenNavGraph(
            routineMainScreenContent = routineMainScreenContent,
            addTaskScreenContent = addTaskScreenContent
        )
        composable(Screen.Habits.route) {
            habitsScreenContent()
        }
        composable(Screen.Settings.route) {
            settingsScreenContent()
        }
    }
}
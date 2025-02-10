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
    routineMainScreenContent: @Composable () -> Unit,
    addTaskScreenContent: @Composable (Int) -> Unit,
    habitsScreenContent: @Composable () -> Unit,
    settingsScreenContent: @Composable () -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route
    ) {
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
package com.smialko.reminderhabitstracker.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.smialko.reminderhabitstracker.navigation.Screen.Companion.KEY_TASK_ID

fun NavGraphBuilder.homeScreenNavGraph(
    routineMainScreenContent: @Composable () -> Unit,
    addTaskScreenContent: @Composable (Int) -> Unit,
) {
    navigation(
        startDestination = Screen.ListTasks.route,
        route = Screen.Home.route
    ) {
        composable(Screen.ListTasks.route) {
            routineMainScreenContent()
        }
        composable(
            route = Screen.AddTask.route,
            arguments = listOf(navArgument(KEY_TASK_ID) {
                type = NavType.IntType
            })
        ) {//tasks/{taskId}
            val toDoTaskId = it.arguments?.getInt(KEY_TASK_ID) ?: 0
            addTaskScreenContent(toDoTaskId)
        }
    }
}
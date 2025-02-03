package com.smialko.reminderhabitstracker.navigation.destinations

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.smialko.reminderhabitstracker.presentation.screens.taskContent.TaskScreen
import com.smialko.reminderhabitstracker.presentation.ui.viewModel.SharedViewModel
import com.smialko.reminderhabitstracker.utils.Constants.TASK_ARGUMENT_KEY
import com.smialko.reminderhabitstracker.utils.Constants.TASK_SCREEN

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun NavGraphBuilder.taskComposable(
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    composable(
        route = TASK_SCREEN,
        arguments = listOf(navArgument(TASK_ARGUMENT_KEY) {
            type = NavType.IntType
        })
    ) { navBackStackEntry ->
        val taskId = navBackStackEntry.arguments?.getInt(TASK_ARGUMENT_KEY) ?: 0

        LaunchedEffect(taskId) {
            sharedViewModel.getSelectedTask(taskId)
        }

        val selectedTask by sharedViewModel.selectedTask.collectAsState()

        LaunchedEffect(selectedTask) {
            if (selectedTask != null || taskId == -1) {
                sharedViewModel.updateTaskFields(selectedTask)
            }
        }
        TaskScreen(selectedTask, navController, sharedViewModel)
    }
}
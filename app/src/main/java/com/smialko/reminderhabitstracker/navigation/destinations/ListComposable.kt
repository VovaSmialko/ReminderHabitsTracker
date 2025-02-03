package com.smialko.reminderhabitstracker.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.smialko.testreminder.presentation.screens.listReminderContent.ListScreen
import com.smialko.reminderhabitstracker.presentation.ui.viewModel.SharedViewModel
import com.smialko.reminderhabitstracker.utils.Constants.LIST_SCREEN

fun NavGraphBuilder.listComposable(
    navigateToTaskScreen: (taskId: Int) -> Unit,
    sharedViewModel: SharedViewModel
) {
    composable(
        route = LIST_SCREEN
    ) {
        ListScreen(navigateToTaskScreen = navigateToTaskScreen, sharedViewModel)
    }
}

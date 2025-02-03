package com.smialko.reminderhabitstracker.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.smialko.reminderhabitstracker.navigation.destinations.listComposable
import com.smialko.reminderhabitstracker.navigation.destinations.taskComposable
import com.smialko.reminderhabitstracker.presentation.ui.viewModel.SharedViewModel
import com.smialko.reminderhabitstracker.utils.Constants.LIST_SCREEN

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    val screen = remember(navHostController) {
        Screens(navHostController)

    }

    NavHost(navController = navHostController, startDestination = LIST_SCREEN) {
        listComposable(screen.task, sharedViewModel)
        taskComposable(navHostController, sharedViewModel)
    }
}
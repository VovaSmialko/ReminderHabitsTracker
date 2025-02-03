package com.smialko.reminderhabitstracker.navigation

import androidx.navigation.NavController
import com.smialko.reminderhabitstracker.utils.Constants.LIST_SCREEN

class Screens(navController: NavController) {
    val list: () -> Unit = {
        navController.navigate("list") {
            popUpTo(LIST_SCREEN) { inclusive = true }
        }
    }

    val task: (Int) -> Unit = { taskId ->
        navController.navigate("task/$taskId")
    }
}

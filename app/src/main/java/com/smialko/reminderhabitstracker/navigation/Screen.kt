package com.smialko.reminderhabitstracker.navigation

sealed class Screen(
    val route: String
) {

    data object ListTasks : Screen(ROUTE_LIST_TASKS)
    data object Habits : Screen(ROUTE_HABITS)
    data object Settings : Screen(ROUTE_SETTINGS)
    data object Home : Screen(ROUTE_HOME)

    object AddTask : Screen(ROUTE_ADD_TASK) {

        private const val ROUTE_FOR_ARGS = "task"

        fun getRouteWithArgs(taskId: Int): String {
            return "$ROUTE_FOR_ARGS/${taskId}"
        }

    }


    companion object {

        const val KEY_TASK_ID = "taskId"
        const val ROUTE_HOME = "home"
        const val ROUTE_LIST_TASKS = "listTasks"
        const val ROUTE_ADD_TASK = "task/{$KEY_TASK_ID}"
        const val ROUTE_HABITS = "habits"
        const val ROUTE_SETTINGS = "settings"
    }

}
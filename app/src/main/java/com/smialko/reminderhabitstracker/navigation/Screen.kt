package com.smialko.reminderhabitstracker.navigation

sealed class Screen(
    val route: String
) {

    data object ListTasks : Screen(ROUTE_LIST_TASKS)
    data object Habits : Screen(ROUTE_HABITS)
    data object Settings : Screen(ROUTE_SETTINGS)
    data object Home : Screen(ROUTE_HOME)

    data object Splash : Screen(ROUTE_SPLASH)
    data object Login : Screen(ROUTE_LOGIN)
    data object SingUp : Screen(ROUTE_SIGN_UP)
    data object ForgotPassword : Screen(ROUTE_FORGOT_PASS)
    data object ResetPassword : Screen(ROUTE_RESET_PASS)

    data object AddTask : Screen(ROUTE_ADD_TASK) {

        private const val ROUTE_FOR_ARGS = "task"

        fun getRouteWithArgs(taskId: Int): String {
            return "$ROUTE_FOR_ARGS/${taskId}"
        }

    }


    companion object {
        const val KEY_TASK_ID = "taskId"

        const val ROUTE_SPLASH = "splash"
        const val ROUTE_SIGN_UP = "signUp"
        const val ROUTE_LOGIN = "login"
        const val ROUTE_FORGOT_PASS = "forgot_password"
        const val ROUTE_RESET_PASS = "reset_password"

        const val ROUTE_HOME = "home"
        const val ROUTE_LIST_TASKS = "listTasks"
        const val ROUTE_ADD_TASK = "task/{$KEY_TASK_ID}"

        const val ROUTE_HABITS = "habits"
        const val ROUTE_SETTINGS = "settings"
    }

}
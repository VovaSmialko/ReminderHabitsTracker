package com.smialko.reminderhabitstracker.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.smialko.reminderhabitstracker.R

sealed class Screen(
    val route: String,
    @StringRes val title: Int? = null,
    @DrawableRes val icon: Int? = null,
) {

    data object ListTasks : Screen(ROUTE_LIST_TASKS)
    data object Habits : Screen(ROUTE_HABITS)
    data object Profile : Screen(ROUTE_PROFILE)
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

    data object Settings : Screen(
        ROUTE_SETTINGS,
        title = R.string.settings,
        icon = R.drawable.ic_settings_profile,
    )
    data object PrivacyPolicies : Screen(
        ROUTE_SETTINGS,
        title = R.string.privacy_and_policies,
        icon = R.drawable.ic_lock,
    )
    data object TermsConditions : Screen(
        ROUTE_SETTINGS,
        title = R.string.terms_and_conditions,
        icon = R.drawable.ic_terms,
    )


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
        const val ROUTE_PROFILE = "profile"

        const val ROUTE_SETTINGS = "settings"
        const val ROUTE_POLICIES = "privacy_policies"
        const val ROUTE_CONDITIONS = "terms-conditions"
    }
}
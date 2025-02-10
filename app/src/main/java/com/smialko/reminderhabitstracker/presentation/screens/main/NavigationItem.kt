package com.smialko.reminderhabitstracker.presentation.screens.main

import androidx.annotation.DrawableRes
import com.smialko.reminderhabitstracker.R
import com.smialko.reminderhabitstracker.navigation.Screen

sealed class NavigationItem(
    val screen: Screen,
    val titleResId: Int,
    @DrawableRes val icon: Int
) {


    data object Home : NavigationItem(
        screen = Screen.Home,
        titleResId = R.string.navigation_item_tasks,
        icon = R.drawable.ic_heatmap
    )

    data object Habits : NavigationItem(
        screen = Screen.Habits,
        titleResId = R.string.navigation_item_habits,
        icon = R.drawable.ic_habit
    )

    data object Settings : NavigationItem(
        screen = Screen.Settings,
        titleResId = R.string.navigation_item_Settings,
        icon = R.drawable.ic_settings
    )
}
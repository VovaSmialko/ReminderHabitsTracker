package com.smialko.reminderhabitstracker.presentation.screens.main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.smialko.reminderhabitstracker.R
import com.smialko.reminderhabitstracker.navigation.AppNavGraph
import com.smialko.reminderhabitstracker.navigation.Screen
import com.smialko.reminderhabitstracker.navigation.rememberNavigationState
import com.smialko.reminderhabitstracker.presentation.onBoarding.forgotPassword.ForgotPasswordScreen
import com.smialko.reminderhabitstracker.presentation.onBoarding.loginScreen.LoginScreen
import com.smialko.reminderhabitstracker.presentation.onBoarding.resetPassword.ResetPasswordScreen
import com.smialko.reminderhabitstracker.presentation.onBoarding.signUp.SignupScreen
import com.smialko.reminderhabitstracker.presentation.onBoarding.splashScreen.SplashScreen
import com.smialko.reminderhabitstracker.presentation.screens.listReminderContent.ListScreen
import com.smialko.reminderhabitstracker.presentation.screens.settings.SettingScreen
import com.smialko.reminderhabitstracker.presentation.screens.taskContent.TaskScreen

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun MainScreen(
) {
    val navigationState = rememberNavigationState()

    val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val hideBottomBarRoutes = listOf(
        Screen.Splash.route,
        Screen.Login.route,
        Screen.SingUp.route,
        Screen.ForgotPassword.route,
        Screen.ResetPassword.route
    )
    val showBottomBar = currentRoute !in hideBottomBarRoutes

    Scaffold(
        bottomBar = {
            if (showBottomBar)
                NavigationBar(
                    modifier = Modifier.fillMaxWidth(),
                    containerColor = MaterialTheme.colorScheme.background,
                    tonalElevation = 10.dp
                ) {
                    val items = listOf(
                        NavigationItem.Home,
                        NavigationItem.Habits,
                        NavigationItem.Settings
                    )
                    items.forEach { item ->
                        val selected = navBackStackEntry?.destination?.hierarchy?.any {
                            it.route == item.screen.route
                        } ?: false
                        NavigationBarItem(
                            selected = selected,
                            onClick = {
                                if (!selected) {
                                    navigationState.navigateTo(item.screen.route)
                                }
                            },
                            icon = {
                                Icon(
                                    painter = painterResource(id = item.icon),
                                    contentDescription = null
                                )
                            },
                            label = {
                                Text(text = stringResource(id = item.titleResId))
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = MaterialTheme.colorScheme.tertiary,
                                selectedTextColor = MaterialTheme.colorScheme.tertiary,
                                unselectedIconColor = colorResource(id = R.color.body),
                                unselectedTextColor = colorResource(id = R.color.body),
                                indicatorColor = MaterialTheme.colorScheme.background.copy(0.5f)
                            )
                        )
                    }
                }
        }
    ) { paddingValues ->
        AppNavGraph(
            navHostController = navigationState.navHostController,
            splashScreeContent = {
                SplashScreen(navController = navigationState.navHostController)
            },
            loginScreenContent = {
                LoginScreen(
                    navController = navigationState.navHostController
                )
            },
            signUpScreenContent = {
                SignupScreen(
                    navController = navigationState.navHostController
                )
            },
            forgotPasswordScreenContent = {
                ForgotPasswordScreen(navController = navigationState.navHostController)
            },
            resetPasswordScreenContent = {
                ResetPasswordScreen(navController = navigationState.navHostController)
            },
            routineMainScreenContent = {
                val tasksViewModel: TasksViewModel = hiltViewModel()
                ListScreen(
                    paddingValues = paddingValues,
                    navigateToTaskScreen = { taskId -> navigationState.navigateToAddTask(taskId) },
                    tasksViewModel = tasksViewModel
                )
            },
            addTaskScreenContent = { taskId ->
                val tasksViewModel: TasksViewModel = hiltViewModel()
                LaunchedEffect(taskId) {
                    tasksViewModel.getSelectedTask(taskId)
                }

                val selectedTask by tasksViewModel.selectedTask.collectAsState()

                LaunchedEffect(selectedTask) {
                    if (selectedTask != null || taskId == -1) {
                        tasksViewModel.updateTaskFields(selectedTask)
                    }
                }
                TaskScreen(selectedTask, navigationState.navHostController, tasksViewModel)
            },
            habitsScreenContent = { TextCounter(name = "Habits") },
            settingsScreenContent = { SettingScreen() })
    }
}

@Composable
private fun TextCounter(name: String) {
    var count by rememberSaveable {
        mutableStateOf(0)
    }

    Text(
        modifier = Modifier.clickable { count++ },
        text = "$name Count: $count",
        color = Color.Black
    )
}
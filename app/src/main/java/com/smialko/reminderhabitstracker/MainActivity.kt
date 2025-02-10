package com.smialko.reminderhabitstracker

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import com.smialko.reminderhabitstracker.presentation.screens.main.MainScreen
import com.smialko.reminderhabitstracker.presentation.screens.main.TasksViewModel
import com.smialko.reminderhabitstracker.ui.theme.ReminderHabitsTrackerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val tasksViewModel: TasksViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ReminderHabitsTrackerTheme {
                MainScreen(tasksViewModel)
            }
        }
    }
}

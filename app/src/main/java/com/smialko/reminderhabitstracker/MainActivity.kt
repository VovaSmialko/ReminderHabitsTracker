package com.smialko.reminderhabitstracker

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.smialko.reminderhabitstracker.ui.theme.ReminderHabitsTrackerTheme
import com.smialko.reminderhabitstracker.navigation.AppNavGraph
import com.smialko.reminderhabitstracker.presentation.ui.viewModel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    private val sharedViewModel: SharedViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ReminderHabitsTrackerTheme {
                navController = rememberNavController()
                AppNavGraph(navController, sharedViewModel)
            }
        }
    }
}

package com.smialko.testreminder.presentation.screens.listReminderContent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.smialko.reminderhabitstracker.presentation.screens.listReminderContent.ListContent
import com.smialko.reminderhabitstracker.presentation.screens.listReminderContent.NewTaskAppBar
import com.smialko.reminderhabitstracker.presentation.ui.viewModel.SharedViewModel

@Composable
fun ListScreen(
    navigateToTaskScreen: (taskId: Int) -> Unit,
    sharedViewModel: SharedViewModel,
) {
    LaunchedEffect(key1 = true) {
        sharedViewModel.getAllTasks()
        sharedViewModel.readSortState()
    }

    val allTasks = sharedViewModel.allTasks.collectAsState()

    val sortState = sharedViewModel.sortState.collectAsState()
    val lowPriority = sharedViewModel.lowPriorityTasks.collectAsState()
    val highPriority = sharedViewModel.highPriorityTasks.collectAsState()

    Scaffold(
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.surface,
        topBar = {
            NewTaskAppBar(
                sharedViewModel = sharedViewModel,
                onAddTaskClicked = navigateToTaskScreen
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(padding)
        ) {
            HorizontalDivider(color = Color.DarkGray)
            ListContent(
                tasks = allTasks.value,
                lowPriority = lowPriority.value,
                highPriority = highPriority.value,
                sortState = sortState.value,
                navigateToTaskScreen = navigateToTaskScreen,
                sharedViewModel = sharedViewModel,
            )
        }
    }
}

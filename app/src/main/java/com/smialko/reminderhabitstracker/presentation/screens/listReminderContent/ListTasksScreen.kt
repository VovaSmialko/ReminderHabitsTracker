package com.smialko.reminderhabitstracker.presentation.screens.listReminderContent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import com.smialko.reminderhabitstracker.domain.entity.ToDoTask
import com.smialko.reminderhabitstracker.presentation.screens.main.TasksViewModel

@Composable
fun ListScreen(
    paddingValues: PaddingValues,
    navigateToTaskScreen: (taskId: Int) -> Unit,
    tasksViewModel: TasksViewModel,
) {
    LaunchedEffect(key1 = true) {
        tasksViewModel.getAllTasks()
        tasksViewModel.readSortState()
    }

    val allTasks = tasksViewModel.allTasks.collectAsState()

    val sortState = tasksViewModel.sortState.collectAsState()
    val lowPriority = tasksViewModel.lowPriorityTasks.collectAsState()
    val highPriority = tasksViewModel.highPriorityTasks.collectAsState()

    Scaffold(
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.surface,
        topBar = {
            NewTaskAppBar(
                tasksViewModel = tasksViewModel,
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
                tasksViewModel = tasksViewModel,
            )
        }
    }
}

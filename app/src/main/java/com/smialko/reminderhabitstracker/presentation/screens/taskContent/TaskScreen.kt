package com.smialko.reminderhabitstracker.presentation.screens.taskContent

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.smialko.reminderhabitstracker.domain.entity.Priority
import com.smialko.reminderhabitstracker.domain.entity.Repeats
import com.smialko.reminderhabitstracker.domain.entity.ToDoTask
import com.smialko.reminderhabitstracker.presentation.screens.main.TasksViewModel

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun TaskScreen(
    selectedTask: ToDoTask?,
    navController: NavHostController,
    tasksViewModel: TasksViewModel
) {

    val title: String by tasksViewModel.title
    val priority: Priority by tasksViewModel.priority
    val date: String by tasksViewModel.date
    val time: String by tasksViewModel.time
    val repeat: Repeats by tasksViewModel.repeat

    val context = LocalContext.current

    Scaffold(
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.surface,
        topBar = {
            TaskCenterAppBar(selectedTask, navController, tasksViewModel)

        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            TaskContent(
                title = title,
                onTitleChange = {
                    tasksViewModel.updateTitle(it)
                },
                priority = priority,
                onPrioritySelected = {
                    tasksViewModel.priority.value = it
                },
                repeat = repeat,
                onRepeatsSelected = {
                    tasksViewModel.repeat.value = it
                },
                date = date,
                onDateChange = {
                    tasksViewModel.updateDate(it)
                },
                time = time,
                onTimeChange = {
                    tasksViewModel.updateTime(it)
                },
                context = context,
                navController = NavHostController(context)
            )
        }
    }
}
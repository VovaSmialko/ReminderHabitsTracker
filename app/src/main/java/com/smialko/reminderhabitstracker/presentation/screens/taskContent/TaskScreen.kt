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
import com.smialko.reminderhabitstracker.presentation.ui.viewModel.SharedViewModel

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun TaskScreen(
    selectedTask: ToDoTask?,
    navController: NavController,
    sharedViewModel: SharedViewModel
) {

    val title: String by sharedViewModel.title
    val priority: Priority by sharedViewModel.priority
    val date: String by sharedViewModel.date
    val time: String by sharedViewModel.time
    val repeat: Repeats by sharedViewModel.repeat

    val context = LocalContext.current

    Scaffold(
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.surface,
        topBar = {
            TaskCenterAppBar(selectedTask, navController, sharedViewModel)

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
                    sharedViewModel.updateTitle(it)
                },
                priority = priority,
                onPrioritySelected = {
                    sharedViewModel.priority.value = it
                },
                repeat = repeat,
                onRepeatsSelected = {
                    sharedViewModel.repeat.value = it
                },
                date = date,
                onDateChange = {
                    sharedViewModel.updateDate(it)
                },
                time = time,
                onTimeChange = {
                    sharedViewModel.updateTime(it)
                },
                context = context,
                navController = NavHostController(context)
            )
        }
    }
}
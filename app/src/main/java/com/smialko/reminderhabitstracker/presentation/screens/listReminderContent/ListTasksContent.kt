package com.smialko.reminderhabitstracker.presentation.screens.listReminderContent

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.rounded.Cancel
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smialko.reminderhabitstracker.R
import com.smialko.reminderhabitstracker.domain.entity.Priority
import com.smialko.reminderhabitstracker.domain.entity.ToDoTask
import com.smialko.reminderhabitstracker.ui.theme.MEDIUM_PADDING
import com.smialko.reminderhabitstracker.ui.theme.PRIORITY_INDICATOR_SIZE_LIST
import com.smialko.reminderhabitstracker.presentation.screens.main.TasksViewModel
import com.smialko.reminderhabitstracker.presentation.screens.main.RequestState

@Composable
fun ListContent(
    tasks: RequestState<List<ToDoTask>>,
    lowPriority: List<ToDoTask>,
    highPriority: List<ToDoTask>,
    sortState: RequestState<Priority>,
    navigateToTaskScreen: (taskId: Int) -> Unit,
    tasksViewModel: TasksViewModel,
) {
    if (sortState is RequestState.Success) {
        when (sortState.data) {
            Priority.NONE -> {
                if (tasks is RequestState.Success) {
                    HandleListContent(
                        tasks = tasks.data,
                        navigateToTaskScreen = navigateToTaskScreen,
                        tasksViewModel
                    )
                }
            }

            Priority.LOW -> {
                HandleListContent(
                    tasks = lowPriority,
                    navigateToTaskScreen = navigateToTaskScreen,
                    tasksViewModel
                )
            }

            Priority.HIGH -> {
                HandleListContent(
                    tasks = highPriority,
                    navigateToTaskScreen = navigateToTaskScreen,
                    tasksViewModel
                )
            }

            Priority.MEDIUM -> {}
        }
    }
}


@Composable
fun DisplayTasks(
    tasks: List<ToDoTask>,
    tasksViewModel: TasksViewModel,
    navigateToTaskScreen: (taskId: Int) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(bottom = 76.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    )
    {
        items(tasks) { task ->
            Log.d("DisplayTasks", "Rendering element: $task")
            TaskItem(task, navigateToTaskScreen, tasksViewModel)
        }
    }
}

@Composable
fun TaskItem(
    toDoTask: ToDoTask,
    navigateToTaskScreen: (taskId: Int) -> Unit,
    tasksViewModel: TasksViewModel,
) {

    var showMenu by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .padding(10.dp)
            .shadow(
                elevation = 16.dp,
                spotColor = Color(0xff0080FF),
                shape = MaterialTheme.shapes.extraLarge
            ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    ) {
        Row(
            modifier = Modifier
                .padding(top = 10.dp, end = 10.dp)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .padding(MEDIUM_PADDING),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Canvas(modifier = Modifier.size(PRIORITY_INDICATOR_SIZE_LIST)) {
                    drawCircle(color = toDoTask.priority.color)
                }
                Column {
                    Text(
                        modifier = Modifier.padding(start = 10.dp),
                        text = toDoTask.title,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        modifier = Modifier.padding(start = 10.dp),
                        text = toDoTask.date,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    IconButton(onClick = { showMenu = true }) {
                        Icon(
                            modifier = Modifier
                                .size(35.dp),
                            imageVector = Icons.Default.MoreHoriz,
                            tint = MaterialTheme.colorScheme.onBackground.copy(0.5f),
                            contentDescription = "Menu"
                        )
                    }
                    DropdownMenu(modifier = Modifier
                        .background(
                            MaterialTheme.colorScheme.surface,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .border(
                            width = 1.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(8.dp)
                        ),
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text(stringResource(R.string.edit)) },
                            onClick = {
                                showMenu = false
                                navigateToTaskScreen(toDoTask.id)
                            }
                        )
                        HorizontalDivider(
                            thickness = 1.dp,
                            color = Color.Black
                        )
                        DropdownMenuItem(
                            text = { Text(stringResource(R.string.delete), color = Color.Red) },
                            onClick = {
                                showMenu = false
                                tasksViewModel.deleteTask(toDoTask)
                            }
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .padding(MEDIUM_PADDING)
                    .fillMaxHeight()
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = toDoTask.time,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(horizontalArrangement = Arrangement.Start) {
                        IconButton(
                            onClick = {}) {
                            Icon(
                                modifier = Modifier
                                    .size(35.dp)
                                    .border(2.6.dp, Color.DarkGray, CircleShape),
                                imageVector = Icons.Rounded.CheckCircle,
                                contentDescription = "Confirm",
                                tint = Color(0xFF55D655)
                            )
                        }
                        IconButton(onClick = {}) {
                            Icon(
                                modifier = Modifier
                                    .size(35.dp)
                                    .border(2.6.dp, Color.DarkGray, CircleShape),
                                imageVector = Icons.Rounded.Cancel,
                                contentDescription = "Cancel",
                                tint = Color(0xFFFF6666),
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HandleListContent(
    tasks: List<ToDoTask>,
    navigateToTaskScreen: (taskId: Int) -> Unit,
    tasksViewModel: TasksViewModel,
) {
    if (tasks.isEmpty()) {
        EmptyReminderContent()
    }
    DisplayTasks(
        tasks = tasks,
        tasksViewModel = tasksViewModel,
        navigateToTaskScreen = navigateToTaskScreen,
    )
}
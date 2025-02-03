package com.smialko.reminderhabitstracker.presentation.screens.listReminderContent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smialko.reminderhabitstracker.R
import com.smialko.reminderhabitstracker.components.PriorityItem
import com.smialko.reminderhabitstracker.domain.entity.Priority
import com.smialko.reminderhabitstracker.presentation.ui.viewModel.SharedViewModel

@Composable
fun NewTaskAppBar(
    onAddTaskClicked: (taskId: Int) -> Unit,
    sharedViewModel: SharedViewModel
) {
    DefaultListCenterAppBar(
        onSortClicked = { sharedViewModel.persistSortState(it) },
        onAddTaskClicked = onAddTaskClicked
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultListCenterAppBar(
    onSortClicked: (Priority) -> Unit,
    onAddTaskClicked: (taskId: Int) -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_reminder),
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
        },
        actions = {
            ListAppBarActions(onSortClicked = onSortClicked, onAddTaskClicked)
        }
    )
}

@Composable
fun ListAppBarActions(
    onSortClicked: (Priority) -> Unit,
    onAddTaskClicked: (taskId: Int) -> Unit
) {
    SortAction(onSortClicked)
    AddAction(onAddTaskClicked)

}

@Composable
fun SortAction(onSortClicked: (Priority) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(onClick = { expanded = true }) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.Sort,
            contentDescription = stringResource(id = R.string.sort_action),
            tint = colorResource(id = R.color.textcolor)
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { PriorityItem(priority = Priority.LOW) },
                onClick = {
                    expanded = false
                    onSortClicked(Priority.LOW)
                }
            )
            DropdownMenuItem(
                text = { PriorityItem(priority = Priority.HIGH) },
                onClick = {
                    expanded = false
                    onSortClicked(Priority.HIGH)
                }
            )
            DropdownMenuItem(
                text = { PriorityItem(priority = Priority.NONE) },
                onClick = {
                    expanded = false
                    onSortClicked(Priority.NONE)
                }
            )
        }
    }
}

@Composable
fun AddAction(onAddTaskClicked: (taskId: Int) -> Unit) {
    IconButton(
        modifier = Modifier.padding(end = 16.dp),
        onClick = { onAddTaskClicked(-1) }) {
        Icon(
            modifier = Modifier
                .size(30.dp)
                .background(Color(0xff007AFF), shape = RoundedCornerShape(50.dp)),
            imageVector = Icons.Rounded.Add,
            contentDescription = "Add",
            tint = MaterialTheme.colorScheme.surface
        )
    }
}
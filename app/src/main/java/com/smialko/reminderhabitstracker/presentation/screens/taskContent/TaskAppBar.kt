package com.smialko.reminderhabitstracker.presentation.screens.taskContent

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Cancel
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.smialko.reminderhabitstracker.R
import com.smialko.reminderhabitstracker.domain.entity.ToDoTask
import com.smialko.reminderhabitstracker.presentation.ui.viewModel.SharedViewModel

@Composable
fun TaskCenterAppBar(
    selectedTask: ToDoTask?,
    navController: NavController,
    sharedViewModel: SharedViewModel,
) {
    ExistingTaskAppBar(
        selectedTask = selectedTask,
        navController = navController,
        sharedViewModel = sharedViewModel
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExistingTaskAppBar(
    selectedTask: ToDoTask?,
    navController: NavController,
    sharedViewModel: SharedViewModel,
) {
    CenterAlignedTopAppBar(
        navigationIcon = {
            CloseAction(navController = navController)
        },
        title = {
            Text(
                text = stringResource(id = R.string.app_reminder),
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
        },
        actions = {
            UpdateAddAction(
                selectedTask = selectedTask,
                navController = navController,
                sharedViewModel = sharedViewModel,
            )
        }
    )
}


@Composable
fun CloseAction(navController: NavController) {
    IconButton(modifier = Modifier
        .padding(start = 16.dp), onClick = { navController.navigateUp() }) {
        Icon(
            modifier = Modifier
                .size(30.dp)
                .border(2.2.dp, Color.DarkGray, CircleShape),
            imageVector = Icons.Rounded.Cancel,
            contentDescription = "Cancel",
            tint = Color(0xFFFF6666),
        )
    }
}

@Composable
fun UpdateAddAction(
    selectedTask: ToDoTask?,
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    val context = LocalContext.current
    IconButton(
        modifier = Modifier.padding(end = 16.dp),
        onClick = {
            if (selectedTask == null) {
                if (sharedViewModel.validateFields()) {
                    sharedViewModel.addTask()
                    navController.navigateUp()
                } else {
                    displayToast(context)
                }
            } else {
                sharedViewModel.updateTask()
                navController.navigateUp()
            }
        }) {
        Icon(
            modifier = Modifier
                .size(30.dp)
                .border(2.2.dp, Color.DarkGray, CircleShape),
            imageVector = Icons.Rounded.CheckCircle,
            contentDescription = "Confirm",
            tint = Color(0xFF55D655)
        )
    }
}

fun displayToast(context: Context) {
    Toast.makeText(context, "Fields Empty", Toast.LENGTH_SHORT).show()
}
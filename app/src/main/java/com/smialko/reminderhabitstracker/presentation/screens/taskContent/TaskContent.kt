package com.smialko.reminderhabitstracker.presentation.screens.taskContent

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.EditCalendar
import androidx.compose.material.icons.outlined.Repeat
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimeInput
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.smialko.reminderhabitstracker.R
import com.smialko.reminderhabitstracker.components.PriorityDropdown
import com.smialko.reminderhabitstracker.components.RepeatDropdown
import com.smialko.reminderhabitstracker.domain.entity.Priority
import com.smialko.reminderhabitstracker.domain.entity.Repeats
import com.smialko.reminderhabitstracker.presentation.notification.workers.RemindMeWorker
import com.smialko.reminderhabitstracker.ui.theme.MEDIUM_PADDING
import com.smialko.reminderhabitstracker.ui.theme.REPEAT_DROPDOWN_HEIGHT
import com.smialko.reminderhabitstracker.utils.Tools
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.concurrent.TimeUnit

@SuppressLint("UnrememberedMutableState")
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskContent(
    title: String,
    repeat: Repeats,
    onRepeatsSelected: (Repeats) -> Unit,
    onTitleChange: (String) -> Unit,
    priority: Priority,
    onPrioritySelected: (Priority) -> Unit,
    date: String,
    onDateChange: (String) -> Unit,
    time: String,
    onTimeChange: (String) -> Unit,
    context: Context,
    navController: NavHostController,
) {

    val showDateDialog = rememberSaveable { mutableStateOf(false) }
    val dateResult = remember { mutableStateOf("") }

    var showTimePicker by remember { mutableStateOf(false) }
    var finalTime by remember { mutableStateOf("") }
    val state = rememberTimePickerState(is24Hour = true)
    val formatter = remember { SimpleDateFormat("hh:mm a", Locale.getDefault()) }
    val snackState = remember { SnackbarHostState() }
    val snackScope = rememberCoroutineScope()

    val notificationPermission = Manifest.permission.POST_NOTIFICATIONS
    val vibratePermission = Manifest.permission.VIBRATE


    val hasNotificationPermission = (
            ContextCompat.checkSelfPermission(
                context,
                notificationPermission
            ) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(
                        context,
                        vibratePermission
                    ) == PackageManager.PERMISSION_GRANTED
            )

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (!isGranted) {
            navController.navigateUp()
        }
    }


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, start = 24.dp, end = 24.dp, bottom = 24.dp)
            .zIndex(1f)
            .offset(y = (-205).dp),
        contentAlignment = Alignment.Center
    ) {
        CardText()
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .imePadding()
            .height(450.dp)
            .padding(20.dp)
            .shadow(
                elevation = 16.dp,
                spotColor = DefaultShadowColor,
                shape = MaterialTheme.shapes.extraLarge
            ),
        shape = MaterialTheme.shapes.extraLarge,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            TextWithIcon(text = "Title", icon = Icons.Outlined.Edit)
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = title,
                onValueChange = { onTitleChange(it) },
                label = { Text(text = stringResource(R.string.insert_title)) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences)
            )
            Spacer(modifier = Modifier.height(6.dp))
            PriorityDropdown(priority = priority, onPrioritySelected = onPrioritySelected)
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextWithIcon(text = "Time", icon = Icons.Outlined.AccessTime)
                Spacer(modifier = Modifier.weight(1f))
                TextWithIcon(text = "Calendar", icon = Icons.Outlined.EditCalendar)
            }
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedButton(
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = MEDIUM_PADDING)
                        .height(REPEAT_DROPDOWN_HEIGHT),
                    shape = MaterialTheme.shapes.extraSmall,
                    onClick = { showTimePicker = true },

                    ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.AccessTime,
                            contentDescription = "Time",
                        )
                        Text(
                            modifier = Modifier.padding(start = 2.dp),
                            color = colorResource(id = R.color.textcolor),
                            text = time,
                            fontSize = 16.sp
                        )
                    }
                }
                Spacer(modifier = Modifier.width(5.dp))
                OutlinedButton(
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = MEDIUM_PADDING)
                        .height(REPEAT_DROPDOWN_HEIGHT),
                    shape = MaterialTheme.shapes.extraSmall,
                    onClick = {
                        showDateDialog.value = true
                    },
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.EditCalendar,
                            contentDescription = "",
                        )
                        Text(
                            modifier = Modifier.padding(start = 4.dp),
                            color = colorResource(id = R.color.textcolor),
                            text = date,
                            fontSize = 13.sp
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            TextWithIcon(text = "Repeat", icon = Icons.Outlined.Repeat)
            Spacer(modifier = Modifier.height(6.dp))
            RepeatDropdown(repeats = repeat, onRepeatSelected = onRepeatsSelected)
        }
        if (showDateDialog.value) {
            val datePickerState = rememberDatePickerState()
            val confirmEnabled = derivedStateOf { true }
            DatePickerDialog(
                onDismissRequest = {
                    showDateDialog.value = false
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            showDateDialog.value = false
                            val dateTime = datePickerState.selectedDateMillis?.let {
                                Tools.convertLongToTime(it)
                            }
                            if (dateTime != null) {
                                dateResult.value = dateTime
                            }
                            onDateChange(dateResult.value)
                        },
                        enabled = confirmEnabled.value
                    ) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            showDateDialog.value = false
                        }
                    ) {
                        Text("Cancel")
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }

        if (showTimePicker) {
            TimePickerDialog(
                onCancel = { showTimePicker = false },
                onConfirm = {
                    val cal = Calendar.getInstance()
                    cal.set(Calendar.HOUR_OF_DAY, state.hour)
                    cal.set(Calendar.MINUTE, state.minute)
                    cal.isLenient = false
                    finalTime = formatter.format(cal.time)
                    snackScope.launch {
                        snackState.showSnackbar("Entered time: $finalTime")
                    }
                    showTimePicker = false

                    onTimeChange(finalTime)

                    val reminderTimeMillis = cal.timeInMillis


                    if (hasNotificationPermission) {
                        setReminder(title, context, reminderTimeMillis)
                    } else {
                        requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                    }
                },
            ) {
                TimeInput(state = state)
            }
        }
    }
}

@Composable
fun TextWithIcon(
    text: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = text,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Medium,
            style = MaterialTheme.typography.bodyMedium
        )
        Icon(
            modifier = Modifier
                .offset(x = (-4).dp, y = (-10).dp)
                .size(20.dp),
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
        )
    }
}

@Composable
fun CardText() {
    Card(
        modifier = Modifier
            .imePadding()
            .width(280.dp)
            .height(30.dp),
        shape = CircleShape,
        colors = CardDefaults.cardColors(containerColor = Color(0xff0080FF))
    ) {
        Text(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center),
            text = stringResource(R.string.make_your_own_reminder),
            color = MaterialTheme.colorScheme.background,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp
        )
    }
}

private fun setReminder(reminderText: String, context: Context, reminderTimeMillis: Long) {

    val constraints = androidx.work.Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()

    val inputData = Data.Builder()
        .putString("reminderText", reminderText)
        .build()

    val request = OneTimeWorkRequestBuilder<RemindMeWorker>()
        .setConstraints(constraints)
        .setInputData(inputData)
        .setInitialDelay(reminderTimeMillis - System.currentTimeMillis(), TimeUnit.MILLISECONDS)
        .build()

    val workManager = WorkManager.getInstance(context)
    workManager.enqueue(request)
}



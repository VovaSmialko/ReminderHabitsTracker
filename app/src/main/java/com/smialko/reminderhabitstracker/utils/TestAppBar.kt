package com.smialko.reminderhabitstracker.utils

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.smialko.reminderhabitstracker.R
import com.smialko.reminderhabitstracker.ui.theme.MEDIUM_PADDING
import com.smialko.reminderhabitstracker.ui.theme.PRIORITY_INDICATOR_SIZE

//FIRST VERSION EMPTY SCREEN WILL BE REMADE IN FUTURE
@Composable
fun EmptyReminderContent(
) {
    Box(
        modifier = Modifier
            .fillMaxSize()

    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.background_page),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.man_for_empty_screen),
            contentDescription = "Empty state illustration",
            modifier = Modifier
                .size(200.dp)
                .padding(bottom = 8.dp)
        )
        Text(
            modifier = Modifier.offset(y = (-30).dp),
            text = stringResource(R.string.add_your_task_first),
            textAlign = TextAlign.Center,
            fontSize = 13.sp

        )
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 36.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.app_reminder),
            fontSize = 20.sp, fontWeight = FontWeight.Normal
        )
        Spacer(modifier = Modifier.padding(end = 10.dp))
        AddTaskButton(onAddTaskClick = {})
    }
}

@Composable
fun AddTaskButton(
    modifier: Modifier = Modifier,
    onAddTaskClick: (taskId: Int) -> Unit
) {
    Box(modifier = modifier.height(40.dp)) {
        Surface(
            modifier = Modifier
                .size(30.dp)
                .align(Alignment.CenterStart)
                .zIndex(1f),
            shape = CircleShape,
            color = MaterialTheme.colorScheme.onSecondary
        ) {
            IconButton(onClick = { onAddTaskClick(-1) }) {
                Icon(
                    modifier = Modifier
                        .padding(5.dp),
                    imageVector = Icons.Default.Add,
                    tint = MaterialTheme.colorScheme.surface,
                    contentDescription = "Add Task",
                )
            }

        }
        Box(
            modifier = Modifier
                .height(18.dp)
                .align(Alignment.Center)
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.15f))
                .padding(start = 36.dp, end = 14.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Add Task",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
    }
}
@Preview
@Composable
fun Cards() {
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
                Canvas(modifier = Modifier.size(PRIORITY_INDICATOR_SIZE)){
                    drawCircle(color= Color.Red)
                }
                Column(
                ){
                    Text(
                        modifier = Modifier.padding(start = 10.dp),
                        text = "toDoTask.title",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        modifier = Modifier.padding(start = 10.dp),
                        text = "13:00",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    IconButton(onClick = {}) {
                        Icon(
                            modifier = Modifier.size(35.dp),
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
                        expanded = false,
                        onDismissRequest = { }
                    ) {
                        DropdownMenuItem(
                            text = { Text(stringResource(R.string.edit)) },
                            onClick = {
                            }
                        )
                        HorizontalDivider(
                            thickness = 1.dp,
                            color = Color.Black
                        )
                        DropdownMenuItem(
                            text = { Text(stringResource(R.string.delete), color = Color.Red) },
                            onClick = {

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
                        text = "13:00",
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
package com.smialko.reminderhabitstracker.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.smialko.reminderhabitstracker.ui.theme.MEDIUM_PADDING
import com.smialko.reminderhabitstracker.ui.theme.PRIORITY_INDICATOR_SIZE
import com.smialko.reminderhabitstracker.domain.entity.Priority

@Composable
fun PriorityItem(priority: Priority) {
    Row (verticalAlignment = Alignment.CenterVertically){
        Canvas(modifier = Modifier.size(PRIORITY_INDICATOR_SIZE)){
            drawCircle(color= priority.color)
        }
        Text(
            modifier = Modifier.padding(start= MEDIUM_PADDING),
            text = priority.name,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
@Preview
fun PriorityItemPreview(){
    PriorityItem(priority = Priority.HIGH)
}
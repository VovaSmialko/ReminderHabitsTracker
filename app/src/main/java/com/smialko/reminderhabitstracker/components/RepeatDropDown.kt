package com.smialko.reminderhabitstracker.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.ContentAlpha
import com.smialko.reminderhabitstracker.R
import com.smialko.reminderhabitstracker.ui.theme.REPEAT_DROPDOWN_HEIGHT
import com.smialko.reminderhabitstracker.domain.entity.Repeats

@Composable
fun RepeatDropdown(
    repeats: Repeats,
    onRepeatSelected: (Repeats) -> Unit
) {

    var expanded by remember {
        mutableStateOf(false)
    }
    val angle: Float by animateFloatAsState(targetValue = if (expanded) 180f else 0f, label = "")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(REPEAT_DROPDOWN_HEIGHT)
            .clickable { expanded = true }
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.30f),
                shape = MaterialTheme.shapes.large
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .padding(start = 10.dp)
                .weight(8f),
            text = repeats.title,
            color = MaterialTheme.colorScheme.primary,
            fontStyle = FontStyle.Normal
        )
        IconButton(
            modifier = Modifier
                .alpha(ContentAlpha.medium)
                .rotate(angle)
                .weight(1.5f),
            onClick = { expanded = true }) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowDown,
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = stringResource(R.string.drop_down_icon)
            )
        }
    }
    DropdownMenu(
        modifier = Modifier
            .fillMaxWidth(fraction = 0.74f)
            .blur(radius = 0.5.dp),
        expanded = expanded,
        onDismissRequest = { expanded = false }) {
        DropdownMenuItem(
            modifier = Modifier.height(35.dp),
            text = { RepeatItem(repeats = Repeats.ONCE) },
            onClick = {
                expanded = false
                onRepeatSelected(Repeats.ONCE)
            }
        )
        DropdownMenuItem(
            modifier = Modifier.height(35.dp),
            text = { RepeatItem(repeats = Repeats.DAILY) },
            onClick = {
                expanded = false
                onRepeatSelected(Repeats.DAILY)
            }
        )
        DropdownMenuItem(
            modifier = Modifier.height(35.dp),
            text = { RepeatItem(repeats = Repeats.MONTOFRI) },
            onClick = {
                expanded = false
                onRepeatSelected(Repeats.MONTOFRI)
            }
        )
    }
}

@Preview
@Composable
private fun RD() {
    RepeatDropdown(repeats = Repeats.DAILY, onRepeatSelected = {})
}

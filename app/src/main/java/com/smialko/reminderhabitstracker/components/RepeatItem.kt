package com.smialko.reminderhabitstracker.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.smialko.reminderhabitstracker.ui.theme.MEDIUM_PADDING
import com.smialko.reminderhabitstracker.domain.entity.Repeats

@Composable
fun RepeatItem(repeats: Repeats) {
    Text(
        modifier = Modifier.padding(start = MEDIUM_PADDING),
        text = repeats.title,
        color = MaterialTheme.colorScheme.onSurface
    )
}

@Preview(showBackground = true)
@Composable
private fun PR() {
    RepeatItem(repeats = Repeats.ONCE)
}
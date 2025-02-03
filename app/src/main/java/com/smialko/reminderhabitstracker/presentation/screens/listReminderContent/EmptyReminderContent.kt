package com.smialko.reminderhabitstracker.presentation.screens.listReminderContent

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smialko.reminderhabitstracker.R


@Composable
fun EmptyReminderContent(
) {
    Box(
        modifier = Modifier
            .fillMaxSize()

    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.bg_page),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.manforbg),
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
        }
    }
}

package com.smialko.reminderhabitstracker.domain.entity

import androidx.compose.ui.graphics.Color
import com.smialko.reminderhabitstracker.ui.theme.HighPriorityColor
import com.smialko.reminderhabitstracker.ui.theme.LowPriorityColor
import com.smialko.reminderhabitstracker.ui.theme.MediumPriorityColor
import com.smialko.reminderhabitstracker.ui.theme.NonePriorityColor

enum class Priority(val color: Color) {
    HIGH(HighPriorityColor),
    MEDIUM(MediumPriorityColor),
    LOW(LowPriorityColor),
    NONE(NonePriorityColor)
}
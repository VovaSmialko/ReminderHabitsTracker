package com.smialko.reminderhabitstracker.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.smialko.reminderhabitstracker.domain.entity.Priority
import com.smialko.reminderhabitstracker.utils.Constants.DATABASE_TABLE

@Entity(tableName = DATABASE_TABLE)
data class ToDoTask(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val title: String,
    val priority: Priority,
    val date: String,
    val time: String
)
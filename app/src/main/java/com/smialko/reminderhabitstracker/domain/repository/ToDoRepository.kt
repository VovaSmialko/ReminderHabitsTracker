package com.smialko.reminderhabitstracker.domain.repository

import com.smialko.reminderhabitstracker.domain.entity.ToDoTask
import kotlinx.coroutines.flow.Flow

interface ToDoRepository {

    val getAllTasks: Flow<List<ToDoTask>>

    val sortByLowPriority: Flow<List<ToDoTask>>

    val sortByHighPriority: Flow<List<ToDoTask>>

    fun getSelectedTask(taskId: Int): Flow<ToDoTask>

    suspend fun addTask(toDoTask: ToDoTask)

    suspend fun updateTask(toDoTask: ToDoTask)

    suspend fun deleteTask(toDoTask: ToDoTask)
}
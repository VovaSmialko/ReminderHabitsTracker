package com.smialko.reminderhabitstracker.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.smialko.reminderhabitstracker.data.db.TodoDao
import com.smialko.reminderhabitstracker.domain.entity.ToDoTask
import com.smialko.reminderhabitstracker.domain.repository.ToDoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class TodoRepositoryImpl @Inject constructor(
    private val todoDao: TodoDao
) : ToDoRepository {

    override val getAllTasks: Flow<List<ToDoTask>> = todoDao.getAllTasks()
    override val sortByLowPriority: Flow<List<ToDoTask>> = todoDao.sortByLowPriority()
    override val sortByHighPriority: Flow<List<ToDoTask>> = todoDao.sortByHighPriority()

    override fun getSelectedTask(taskId: Int): Flow<ToDoTask> {
        return todoDao.getSelectedTask(taskId)
    }

    override suspend fun addTask(toDoTask: ToDoTask) {
        todoDao.addTask(toDoTask)
    }

    override suspend fun updateTask(toDoTask: ToDoTask) {
        todoDao.updateTask(toDoTask)
    }

    override suspend fun deleteTask(toDoTask: ToDoTask) {
        todoDao.deleteTask(toDoTask)
    }
}
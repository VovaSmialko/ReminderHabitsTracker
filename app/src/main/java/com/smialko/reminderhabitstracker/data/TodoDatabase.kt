package com.smialko.reminderhabitstracker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.smialko.reminderhabitstracker.domain.entity.ToDoTask

@Database(entities = [ToDoTask::class], version = 2, exportSchema = false)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao

    companion object {
        private const val DATABASE_NAME = "todo_database"
        private var INSTANCE: TodoDatabase? = null
        private val LOCK = Any()
        fun getInstance(context: Context): TodoDatabase {
            INSTANCE?.let { return it }

            synchronized(LOCK) {
                INSTANCE?.let { return it }

                val database = Room.databaseBuilder(
                    context = context,
                    klass = TodoDatabase::class.java,
                    name = DATABASE_NAME
                )
                    .fallbackToDestructiveMigrationFrom(1, 2)
                    .build()

                INSTANCE = database
                return database
            }
        }
    }
}
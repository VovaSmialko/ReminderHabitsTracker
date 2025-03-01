package com.smialko.reminderhabitstracker.di.modules

import android.content.Context
import com.smialko.reminderhabitstracker.data.db.TodoDatabase
import com.smialko.reminderhabitstracker.di.WorkerKey
import com.smialko.reminderhabitstracker.presentation.notification.workers.ChildWorkerFactory
import com.smialko.reminderhabitstracker.presentation.notification.workers.RemindMeWorker
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): TodoDatabase {
        return TodoDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideDao(db: TodoDatabase) = db.todoDao()

    @Module
    @InstallIn(SingletonComponent::class)
    interface WorkerModule {

        @Binds
        @IntoMap
        @WorkerKey(RemindMeWorker::class)
        fun bindRemindMeWorkerFactory(worker: RemindMeWorker.Factory): ChildWorkerFactory

    }
}



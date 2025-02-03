package com.smialko.reminderhabitstracker.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.smialko.reminderhabitstracker.data.TodoDatabase
import com.smialko.reminderhabitstracker.presentation.notification.workers.ChildWorkerFactory
import com.smialko.reminderhabitstracker.presentation.notification.workers.RemindMeWorker
import com.smialko.reminderhabitstracker.presentation.ui.viewModel.SharedViewModel
import com.smialko.reminderhabitstracker.presentation.ui.viewModel.ViewModelFactory
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
}

@Module
@InstallIn(SingletonComponent::class)
interface ViewModelModule {
    //ViewModel factory
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory


    @IntoMap
    @ViewModelKey(SharedViewModel::class)
    @Binds
    fun bindSharedViewModel(viewModel: SharedViewModel): ViewModel
}

@Module
@InstallIn(SingletonComponent::class)
interface WorkerModule {

    @Binds
    @IntoMap
    @WorkerKey(RemindMeWorker::class)
    fun bindRemindMeWorkerFactory(worker: RemindMeWorker.Factory): ChildWorkerFactory

}




package com.smialko.reminderhabitstracker.di.modules

import com.smialko.reminderhabitstracker.data.repository.AuthenticationRepositoryImpl
import com.smialko.reminderhabitstracker.data.repository.TodoRepositoryImpl
import com.smialko.reminderhabitstracker.data.repository.UserRepositoryImpl
import com.smialko.reminderhabitstracker.domain.repository.AuthenticationRepository
import com.smialko.reminderhabitstracker.domain.repository.ToDoRepository
import com.smialko.reminderhabitstracker.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Singleton
    @Binds
    fun bindTodoRepository(impl: TodoRepositoryImpl): ToDoRepository
}
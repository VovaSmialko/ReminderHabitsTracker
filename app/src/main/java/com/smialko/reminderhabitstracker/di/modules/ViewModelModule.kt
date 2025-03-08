package com.smialko.reminderhabitstracker.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.smialko.reminderhabitstracker.MainViewModel
import com.smialko.reminderhabitstracker.di.ViewModelKey
import com.smialko.reminderhabitstracker.presentation.onBoarding.AuthViewModel
import com.smialko.reminderhabitstracker.presentation.screens.main.TasksViewModel
import com.smialko.reminderhabitstracker.presentation.screens.profile.ProfileViewModel
import com.smialko.reminderhabitstracker.presentation.ui.viewModel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(SingletonComponent::class)
interface ViewModelModule {

    //ViewModel factory
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @IntoMap
    @ViewModelKey(MainViewModel::class)
    @Binds
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    @Binds
    fun bindAuthViewModel(viewModel: AuthViewModel): ViewModel

    @IntoMap
    @ViewModelKey(TasksViewModel::class)
    @Binds
    fun bindSharedViewModel(viewModel: TasksViewModel): ViewModel

    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    @Binds
    fun bindProfileViewModel(viewModel: ProfileViewModel): ViewModel
}
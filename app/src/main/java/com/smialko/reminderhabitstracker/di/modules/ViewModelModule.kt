package com.smialko.reminderhabitstracker.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.smialko.reminderhabitstracker.MainViewModel
import com.smialko.reminderhabitstracker.di.ViewModelKey
import com.smialko.reminderhabitstracker.presentation.onBoarding.AuthViewModel
import com.smialko.reminderhabitstracker.presentation.onBoarding.forgotPassword.ForgotPasswordViewModel
import com.smialko.reminderhabitstracker.presentation.onBoarding.resetPassword.ResetPasswordViewModel
import com.smialko.reminderhabitstracker.presentation.screens.main.TasksViewModel
import com.smialko.reminderhabitstracker.presentation.screens.settings.SettingsViewModel
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
    @ViewModelKey(ResetPasswordViewModel::class)
    @Binds
    fun bindResetPasswordViewModel(viewModel: ResetPasswordViewModel): ViewModel

    @IntoMap
    @ViewModelKey(ForgotPasswordViewModel::class)
    @Binds
    fun bindForgotPasswordViewModel(viewModel: ForgotPasswordViewModel): ViewModel


    @IntoMap
    @ViewModelKey(TasksViewModel::class)
    @Binds
    fun bindSharedViewModel(viewModel: TasksViewModel): ViewModel

    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    @Binds
    fun bindSettingsViewModel(viewModel: SettingsViewModel): ViewModel
}
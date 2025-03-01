package com.smialko.reminderhabitstracker.di.modules

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage
import com.smialko.reminderhabitstracker.data.repository.AuthenticationRepositoryImpl
import com.smialko.reminderhabitstracker.data.repository.UserRepositoryImpl
import com.smialko.reminderhabitstracker.domain.repository.AuthenticationRepository
import com.smialko.reminderhabitstracker.domain.repository.UserRepository
import com.smialko.reminderhabitstracker.domain.usecases.firebaseUsecases.AuthenticationUseCases
import com.smialko.reminderhabitstracker.domain.usecases.firebaseUsecases.FirebaseAuthStateUseCase
import com.smialko.reminderhabitstracker.domain.usecases.firebaseUsecases.FirebaseSignInUseCase
import com.smialko.reminderhabitstracker.domain.usecases.firebaseUsecases.FirebaseSignOutUseCase
import com.smialko.reminderhabitstracker.domain.usecases.firebaseUsecases.FirebaseSignUpUseCase
import com.smialko.reminderhabitstracker.domain.usecases.firebaseUsecases.IsUserAuthenticated
import com.smialko.reminderhabitstracker.domain.usecases.userUsecases.GetUserDetailsUseCase
import com.smialko.reminderhabitstracker.domain.usecases.userUsecases.SetUserDetailsUseCase
import com.smialko.reminderhabitstracker.domain.usecases.userUsecases.UserUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Singleton
    @Provides
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()

    @Singleton
    @Provides
    fun provideFirebaseFirestoreDatabase() = Firebase.firestore

    @Singleton
    @Provides
    fun provideFirebaseStorage() = FirebaseStorage.getInstance()

    @Singleton
    @Provides
    fun provideAuthenticationRepository(
        auth: FirebaseAuth,
        firestore: FirebaseFirestore
    ): AuthenticationRepository {
        return AuthenticationRepositoryImpl(auth, firestore)
    }

    @Singleton
    @Provides
    fun provideAuthUseCases(repository: AuthenticationRepository) = AuthenticationUseCases(
        isUserAuthenticated = IsUserAuthenticated(repository = repository),
        firebaseAuthState = FirebaseAuthStateUseCase(repository = repository),
        firebaseSignInUseCase = FirebaseSignInUseCase(repository = repository),
        firebaseSignUpUseCase = FirebaseSignUpUseCase(repository = repository),
        firebaseSignOutUseCase = FirebaseSignOutUseCase(repository = repository)
    )

    @Singleton
    @Provides
    fun provideUserRepository(firestore: FirebaseFirestore): UserRepository {
        return UserRepositoryImpl(firestore)
    }

    @Singleton
    @Provides
    fun provideUserUseCases(repository: UserRepository) = UserUseCases(
        getUserDetails = GetUserDetailsUseCase(repository),
        setUserDetailsUseCase = SetUserDetailsUseCase(repository)
    )
}

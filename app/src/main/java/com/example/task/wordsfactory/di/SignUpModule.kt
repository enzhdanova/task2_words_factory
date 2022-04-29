package com.example.task.wordsfactory.di

import android.content.Context
import android.content.SharedPreferences
import com.example.task.wordsfactory.data.AuthRepositoryImpl
import com.example.task.wordsfactory.data.UserDataSource
import com.example.task.wordsfactory.ui.AuthRepository
import com.example.task.wordsfactory.ui.viewmodel.SignUpViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SharedPreferenceModule {
    companion object {
        val preferenceWordsFactory = "preferenceWordsFactory"
    }

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(preferenceWordsFactory, Context.MODE_PRIVATE)
    }

}

@Module
@InstallIn(ViewModelComponent::class)
abstract class SignUpViewModelModule {
    @Binds
    abstract fun bindRepo(
        informationRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository
}

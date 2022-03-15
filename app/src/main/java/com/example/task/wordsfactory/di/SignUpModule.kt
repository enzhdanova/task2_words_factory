package com.example.task.wordsfactory.di

import android.content.Context
import android.content.SharedPreferences
import com.example.task.wordsfactory.data.AuthRepositoryImpl
import com.example.task.wordsfactory.data.UserDataSource
import com.example.task.wordsfactory.ui.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
abstract class SignUpModule {

    @Binds
    abstract fun bindRepo(authRepositoryImpl: AuthRepositoryImpl): AuthRepository
}

@Module
@InstallIn(SingletonComponent::class)
class SharedPreferencesModule {

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("preferenceWordsFactory", Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun provideAppContext(@ApplicationContext appContext: Context): AuthRepositoryImpl {
        return AuthRepositoryImpl(
            context = appContext,
            dataSource = UserDataSource(
                sharedPreferences = provideSharedPreference(
                    context = appContext
                ),
                Dispatchers.IO
            )
        )
    }

}
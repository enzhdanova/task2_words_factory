package com.example.task.wordsfactory.di

import com.example.task.wordsfactory.data.InformationRepository
import com.example.task.wordsfactory.data.InformationRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {
    @Binds
    abstract fun bindRepo(
        informationRepositoryImpl: InformationRepositoryImpl
    ) : InformationRepository
}
package com.example.task.wordsfactory.di

import android.content.Context
import com.example.task.wordsfactory.data.InformationRepository
import com.example.task.wordsfactory.data.InformationRepositoryImpl
import com.example.task.wordsfactory.data.mock_data.InformationDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {
    @Binds
    abstract fun bindRepo(
        informationRepositoryImpl: InformationRepositoryImpl
    ) : InformationRepository
}

@Module
@InstallIn(SingletonComponent::class)
class ProductionModule {

    @Singleton
    @Provides
    fun provideAppResourses(@ApplicationContext appContext: Context): InformationRepositoryImpl {
        return InformationRepositoryImpl(context = appContext, informationDataSource = InformationDataSource(context = appContext))
    }

}
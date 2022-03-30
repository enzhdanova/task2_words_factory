package com.example.task.wordsfactory.di

import android.content.Context
import androidx.room.Room
import com.example.task.wordsfactory.data.DictionaryRepositoryImpl
import com.example.task.wordsfactory.database.AppDatabase
import com.example.task.wordsfactory.database.dao.DictionaryDao
import com.example.task.wordsfactory.ui.DictionaryRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DictionaryModule {
}


@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {
    @Binds
    abstract fun bindRepo(
        dictionaryRepositoryImpl: DictionaryRepositoryImpl
    ): DictionaryRepository

}


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideDictionaryDao(appDatabase: AppDatabase) = appDatabase.dictionaryDao()

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext appContext: Context
    ) = Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "database"
        ).fallbackToDestructiveMigration()
        .build()

}


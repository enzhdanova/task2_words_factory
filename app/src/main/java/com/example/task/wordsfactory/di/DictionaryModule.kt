package com.example.task.wordsfactory.di

import android.content.Context
import androidx.room.Room
import com.example.task.wordsfactory.data.DictionaryRepositoryImpl
import com.example.task.wordsfactory.database.AppDatabase
import com.example.task.wordsfactory.network.WordApi
import com.example.task.wordsfactory.ui.DictionaryRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

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

    companion object {
        val database_name = "database"
    }

        @Provides
        fun provideDictionaryDao(appDatabase: AppDatabase) = appDatabase.dictionaryDao()

        @Provides
        @Singleton
        fun provideAppDatabase(
            @ApplicationContext appContext: Context
        ) = Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            database_name
        ).fallbackToDestructiveMigration()
            .build()

}

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule{

    companion object {
        val baseUrl = "https://api.dictionaryapi.dev/"
    }

    @Provides
    @Singleton
    fun provideApi() = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    @Provides
    @Singleton
    fun provideApiService() = provideApi().create(WordApi::class.java)

}

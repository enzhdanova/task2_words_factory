package com.example.task.wordsfactory.ui.view

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import com.example.task.wordsfactory.R
import com.example.task.wordsfactory.data.DictionaryRepositoryImpl
import com.example.task.wordsfactory.data.datasource.LocalDataSource
import com.example.task.wordsfactory.data.datasource.RemoteDataSource
import com.example.task.wordsfactory.database.AppDatabase
import com.example.task.wordsfactory.database.dao.DictionaryDao
import com.example.task.wordsfactory.ui.DictionaryRepository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.scopes.ServiceScoped
import dagger.hilt.android.scopes.ViewScoped
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

@AndroidEntryPoint
class WordsFactoryWidget : AppWidgetProvider() {
    @Inject
    lateinit var appDatabase: AppDatabase

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(appDatabase, context, appWidgetManager, appWidgetId)
        }
    }

}

internal fun updateAppWidget(
    appDatabase: AppDatabase,
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val views = RemoteViews(context.packageName, R.layout.words_factory_widget)

    CoroutineScope(Dispatchers.IO).launch {
        val result = appDatabase.dictionaryDao().getCountWords().toString()

        views.setTextViewText(R.id.count_words_in_dictionary_value, result + " words")
        views.setTextColor(R.id.count_words_in_dictionary_value, context.getColor(R.color.teal_700))
        appWidgetManager.updateAppWidget(appWidgetId, views)
    }

}

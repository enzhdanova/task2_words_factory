package com.example.task.wordsfactory.ui.view

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import com.example.task.wordsfactory.R
import com.example.task.wordsfactory.ui.DictionaryRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject

@AndroidEntryPoint
class WordsFactoryWidget : AppWidgetProvider() {
    @Inject
    lateinit var dictionaryRepository: DictionaryRepository

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(dictionaryRepository, context, appWidgetManager, appWidgetId)
        }
    }
}

internal fun updateAppWidget(
    dictionaryRepository: DictionaryRepository,
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val views = RemoteViews(context.packageName, R.layout.words_factory_widget)

    CoroutineScope(Dispatchers.IO).launch {
        val resultCountWordsInDictionary = dictionaryRepository.getCountWords()

        resultCountWordsInDictionary.onSuccess {
            views.setTextViewText(
                R.id.count_words_in_dictionary_value,
                it.toString() + " " + context.getString(R.string.words)
            )
        }

        val resultCountLearnedWords = dictionaryRepository.getCountLearnedWords()

        resultCountLearnedWords.onSuccess {
            views.setTextViewText(
                R.id.count_words_learned_value,
                it.toString() + " " + context.getString(R.string.words)
            )
        }

        appWidgetManager.updateAppWidget(appWidgetId, views)
    }
}

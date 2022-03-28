package com.example.task.wordsfactory.domain

import com.example.task.wordsfactory.data.Repository.DictionaryRepositoryImpl
import com.example.task.wordsfactory.data.model.Meaning
import com.example.task.wordsfactory.data.model.PartOfSpeech
import com.example.task.wordsfactory.data.model.Word
import com.example.task.wordsfactory.ui.entity.MeaningUI
import com.example.task.wordsfactory.ui.entity.WordUI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class GetWordUseCase(
    private val dictionaryRepositoryImpl: DictionaryRepositoryImpl
) {

    fun getWord(searchWord: String): Result<WordUI> {

        val result = dictionaryRepositoryImpl.getWord(searchWord)

        println(result)
        if (result.isFailure) return Result.failure(Exception("Слово не найдено"))

        result.onSuccess { resWord ->
            println("_______________" + resWord)

            val partOfSpeechList = mutableListOf<PartOfSpeech>()
            val meaningList = mutableListOf<Meaning>()


            resWord.partOfSpeech.forEach { idPofS ->
                val partOfSpeech = dictionaryRepositoryImpl.getPartOfSpeech(idPofS)
                partOfSpeechList.add(partOfSpeech)

                partOfSpeech.meaning.forEach { idM ->
                    meaningList.add(dictionaryRepositoryImpl.getMeaning(idM))
                }
            }


            return Result.success(getWordEntity(resWord, partOfSpeechList, meaningList))
        }

        return Result.failure(Exception("Слово не найдено"))
    }

    private fun getWordEntity(resWord: Word, partOfSpeech: List<PartOfSpeech>, meanings: List<Meaning>): WordUI {

        val partOfSpeechStr = partOfSpeech.joinToString {
            it.partOfSpeech
        }

        val meaningsUI = mutableListOf<MeaningUI>()

        meanings.forEach { meaning ->
            meaningsUI.add(meaningModelToUIEntity(meaning))
        }

        return wordModelToUIEntity(resWord, partOfSpeechStr, meaningsUI)
    }

    private fun meaningModelToUIEntity(meaning: Meaning): MeaningUI {
        return MeaningUI(
            id = meaning.id,
            definition = meaning.definition,
            example = meaning.example
        )
    }

    private fun wordModelToUIEntity(
        word: Word,
        partOfSpeech: String,
        meanings: List<MeaningUI>
    ): WordUI {
        return WordUI(
            id = word.id,
            word = word.word,
            phonetic = word.phonetic,
            partOfSpeech = partOfSpeech,
            meanings = meanings
        )
    }


}
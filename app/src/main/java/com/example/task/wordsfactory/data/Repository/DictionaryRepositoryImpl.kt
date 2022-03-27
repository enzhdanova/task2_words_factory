package com.example.task.wordsfactory.data.Repository

import com.example.task.wordsfactory.data.MockeWordModel
import com.example.task.wordsfactory.data.dataSource.RemoteDataSource
import com.example.task.wordsfactory.data.model.Meaning
import com.example.task.wordsfactory.data.model.PartOfSpeech
import com.example.task.wordsfactory.data.model.Word

class DictionaryRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val wordRepositoryImpl: WordRepositoryImpl,
    private val partOfSpeechRepositoryImpl: PartOfSpeechRepositoryImpl,
    private val meaningRepositoryImpl: MeaningRepositoryImpl
) {
    val partOfSpeechList = mutableListOf<PartOfSpeech>()
    val meaningList = mutableListOf<Meaning>()
    
    fun getWord(searchWord: String) : Result<Word> {
        val result = remoteDataSource.getWord(searchWord)

        if (result.isFailure) {

            val resultWord = wordRepositoryImpl.getWord(searchWord)

            resultWord.onSuccess { word ->
                word.partOfSpeech.forEach {
                    idPofS ->
                    val partOfSpeech = partOfSpeechRepositoryImpl.getPartOfSpeech(idPofS)
                    partOfSpeechList.add(partOfSpeech)

                    partOfSpeech.meaning.forEach { idM ->
                        meaningList.add(meaningRepositoryImpl.getMeaning(idM))
                    }
                }
            }


            return resultWord
        }

        //TODO: ТУТ ВРЕМЕННЫЙ КОД
        result.onSuccess {
            partOfSpeechList.addAll(MockeWordModel.partOfSpeech)
            meaningList.addAll(MockeWordModel.meanings)
            return Result.success(it)
        }

        return Result.failure(Exception("Слово не найдено"))
    }

}
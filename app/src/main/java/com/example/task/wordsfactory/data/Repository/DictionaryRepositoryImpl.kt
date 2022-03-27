package com.example.task.wordsfactory.data.Repository

import com.example.task.wordsfactory.data.dataSource.RemoteDataSource
import com.example.task.wordsfactory.data.model.Meaning
import com.example.task.wordsfactory.data.model.PartOfSpeech
import com.example.task.wordsfactory.data.model.Word
import com.example.task.wordsfactory.network.entity.DefinitionRequest

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
        var map: MutableMap<String, MutableList<DefinitionRequest>> = mutableMapOf()

        result.onSuccess {
           wordRequest ->
            // partOfSpeechList.addAll(MockeWordModel.partOfSpeech)
           // meaningList.addAll(MockeWordModel.meanings)
            wordRequest.meanings.forEach {
                meaningRequest ->
                val res = map[meaningRequest.partOfSpeech]?.plus(meaningRequest.definitions)
                    ?: meaningRequest.definitions
                map[meaningRequest.partOfSpeech] = res.toMutableList()
            }

            map.onEachIndexed{
                index, entry ->
                partOfSpeechList.add(
                    PartOfSpeech(
                        id = index.toLong(),
                        partOfSpeech = entry.key,
                        meaning = listOf()
                    )
                )
                entry.value.forEach {
                        definitionRequest ->
                    meaningList.add(
                        Meaning(
                            id = index.toLong(),
                            definition = definitionRequest.definition,
                            example = definitionRequest.example
                        )
                    )
                }
            }

            val word = Word(0, wordRequest.word, wordRequest.phonetic, listOf())

            return Result.success(word)
        }

        return Result.failure(Exception("Слово не найдено"))
    }

}
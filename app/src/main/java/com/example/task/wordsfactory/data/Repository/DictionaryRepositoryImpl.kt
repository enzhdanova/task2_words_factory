package com.example.task.wordsfactory.data.Repository

import com.example.task.wordsfactory.data.data_source.LocalDataSource
import com.example.task.wordsfactory.data.data_source.RemoteDataSource
import com.example.task.wordsfactory.dao.MeaningDao
import com.example.task.wordsfactory.dao.PartOfSpeechDao
import com.example.task.wordsfactory.dao.WordDao
import com.example.task.wordsfactory.data.model.Meaning
import com.example.task.wordsfactory.data.model.Word

class DictionaryRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {


    suspend fun getWord(searchWord: String): Result<Word> {
        val result = remoteDataSource.getWord(searchWord)
        return if (result.isSuccess) {
            result
        } else {
            localDataSource.getWord(searchWord)
        }
    }



    fun WordToUIEntity(
        wordDao: WordDao,
        partOfSpeechDaos: List<PartOfSpeechDao>,
        meaningDaos: List<MeaningDao>
    ): Word {

        val meaningsUI = meaningToEntity(meaningDaos)
        val partOfSpeechStr = partOfSpeechDaos.joinToString {
            it.partOfSpeech
        }

        return Word(
            word = wordDao.word,
            phonetic = wordDao.phonetic,
            partOfSpeech = partOfSpeechStr,
            meanings = meaningsUI
        )
    }

    fun meaningToEntity(meaningDaos: List<MeaningDao>): List<Meaning> {
        val meaningsUI = mutableListOf<Meaning>()
        meaningDaos.forEach {
            meaningsUI.add(
                Meaning(
                    definition = it.definition,
                    example = it.example
                )
            )
        }
        return meaningsUI
    }

}
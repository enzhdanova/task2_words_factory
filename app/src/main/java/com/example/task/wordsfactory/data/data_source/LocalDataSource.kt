package com.example.task.wordsfactory.data.data_source

import com.example.task.wordsfactory.data.model.Word
import com.example.task.wordsfactory.database.dao.DictionaryDao
import com.example.task.wordsfactory.database.entity.MeaningBD
import com.example.task.wordsfactory.database.entity.WordBD
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val dictionaryDao: DictionaryDao
) {
    suspend fun getWord(searchWord: String): Result<Word> {
        return withContext(Dispatchers.IO) {
            try {
                val res = dictionaryDao.getWord(searchWord)
                val meaning = res.id?.let {
                    dictionaryDao.getMeaning(it).map {
                        it.toModel()
                    }
                } ?: emptyList()
                val wordModel = res.toModelWithMeaning(meaning = meaning)
                Result.success(wordModel)
            } catch (ioe: java.lang.Exception) {
                Result.failure(Exception("Слово не найдено"))
            }
        }
    }

    suspend fun addWord(word: Word): Result<Boolean> {
        return  withContext(Dispatchers.IO) {
            try {

                val wordId = dictionaryDao.insertWord(
                    WordBD(
                        word = word.word,
                        phonetic = word.phonetic,
                        partOfSpeech = word.partOfSpeech
                    )
                )

                val meaningsDB = word.meanings.map {
                    MeaningBD(
                        definition = it.definition,
                        example = it.example,
                        word_id = wordId
                    )
                }

                dictionaryDao.insertMeanings(meaningsDB)

                println("_____________add word_______________________________")
                val wordBD = dictionaryDao.getWord(word.word)
                println(wordBD)
                if (wordBD.id != null)
                println(dictionaryDao.getMeaning(word_id = wordBD.id))
                println("_____________add word_______________________________")
                Result.success(true)
            } catch (ioe: Exception) {
                Result.failure(ioe)
            }
        }
    }

}


/*
            GlobalScope.launch {
                val db = Room.databaseBuilder(
                    requireContext().applicationContext,
                    AppDatabase::class.java,
                    "database"
                )
                    .fallbackToDestructiveMigration()
                    .build()



                println("________${db}_________")
                val wordDao = db.dictionaryDao()
                val word_id: Long = wordDao.insertWord(WordBD(word = wordArg.word, phonetic = wordArg.phonetic, partOfSpeech = wordArg.partOfSpeech))

               // val word_id = wordDao.getWordId(wordArg.word)
                val meaningsList = wordArg.meanings.map {
                    MeaningBD(
                        definition = it.definition,
                        example = it.example,
                        word_id = word_id,
                    )
                }

                wordDao.insertWordWithMeanings(
                    WordBD(word = wordArg.word, phonetic = wordArg.phonetic, partOfSpeech = wordArg.partOfSpeech),
                    meaningsList
                )

          //      wordDao.insertMeanings(meaning = meaningsList)

                println("____________bd_________")
                println(wordDao.getWord(wordArg.word))
                println(wordDao.getMeaning(word_id))

                println("____________bd_________")

            }
*/
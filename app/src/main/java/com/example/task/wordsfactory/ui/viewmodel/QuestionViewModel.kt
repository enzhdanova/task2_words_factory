package com.example.task.wordsfactory.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task.wordsfactory.data.model.Word
import com.example.task.wordsfactory.ui.DictionaryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor(
    private val dictionaryRepository: DictionaryRepository
) : ViewModel() {

    private var wordsForTraining: MutableList<Word> = mutableListOf()

    private val _questionUIState = MutableLiveData(QuestionUIState())
    val questionUIState: LiveData<QuestionUIState> = _questionUIState

    init {
        initUIState()
    }

    private fun initUIState() {
        viewModelScope.launch {
            dictionaryRepository.getTrainingWord().onSuccess {
                wordsForTraining.clear()
                wordsForTraining.addAll(it)

                getQuestion()
            }
        }
    }

    fun getQuestion() : Boolean {
        val numberQuestion = _questionUIState.value?.numberQuestion?.inc() ?: 1

        if (numberQuestion > wordsForTraining.size) return false

        val rightWord = wordsForTraining[numberQuestion - 1].word

        viewModelScope.launch {

            val result = dictionaryRepository.getWrongWordsForQuestion(rightWord)

            result.onSuccess { words ->
                var answers: MutableList<String> = words.map { words ->
                    words.word
                }.toMutableList()
                answers.add(rightWord)
                answers.shuffle()


                _questionUIState.value = _questionUIState.value?.copy(
                    numberQuestion = numberQuestion,
                    countQuestion = wordsForTraining.size,
                    nowQuestion = wordsForTraining[numberQuestion - 1].meanings.shuffled()
                        .first().definition,
                    answer1 = answers[0],
                    answer2 = answers[1],
                    answer3 = answers[2]
                )
            }
        }

        return true
    }


}
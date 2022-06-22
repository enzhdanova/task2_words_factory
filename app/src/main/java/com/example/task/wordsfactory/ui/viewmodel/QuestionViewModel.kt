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

    fun getQuestion(): Boolean {
        val numberQuestion = _questionUIState.value?.numberNowQuestion?.inc() ?: 1

        //TODO: по идее, если у нас последний вопрос, то мы переходим дальше
        if (numberQuestion > wordsForTraining.size) return false

        val rightWord = wordsForTraining[numberQuestion - 1]

        viewModelScope.launch{
            val result = dictionaryRepository.getQuestion(rightWord)

            result.onSuccess {
                _questionUIState.value = _questionUIState.value?.copy(
                    countQuestions = wordsForTraining.size,
                    numberNowQuestion = numberQuestion,
                    nowQuestion = it.nowQuestion,
                    answer1 = it.answers[0],
                    answer2 = it.answers[1],
                    answer3 = it.answers[2]
                )
            }
        }

        return true
    }

}
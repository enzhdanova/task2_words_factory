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

    companion object {
        private const val INCORRECT_INDEX = -1
    }

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

    fun getQuestion() {
        val numberQuestion = _questionUIState.value?.numberNowQuestion?.inc() ?: 1

        if (numberQuestion > wordsForTraining.size) {
            _questionUIState.value = _questionUIState.value?.copy(
                numberNowQuestion = numberQuestion,
            )
            return
        }
        val rightWord = wordsForTraining[numberQuestion - 1]

        viewModelScope.launch {
            val result = dictionaryRepository.getQuestion(rightWord)

            result.onSuccess {
                _questionUIState.value = _questionUIState.value?.copy(
                    countQuestions = wordsForTraining.size,
                    numberNowQuestion = numberQuestion,
                    nowQuestion = it.nowQuestion,
                    answer = it.answers,
                    rightAnswer = rightWord.word,
                    setAnswer = false
                )
            }
        }
    }

    fun isRightAnswer(numberAnswer: Int) =
        if (numberAnswer == INCORRECT_INDEX)
            false
        else {
            _questionUIState.value?.rightAnswer == _questionUIState.value?.answer?.get(numberAnswer)
        }

    fun setAnswer(numberAnswer: Int) {
        val numberNowQuestion = _questionUIState.value?.numberNowQuestion ?: 0
        var studyCoefficient = wordsForTraining[numberNowQuestion - 1].studyCoefficient

        var answer = _questionUIState.value?.countRightAnswer ?: 0
        if (isRightAnswer(numberAnswer)) {
            answer += 1
            studyCoefficient += 1
        } else {
            studyCoefficient -= 1
        }

        updateStudyCoefficient(wordsForTraining[numberNowQuestion - 1], studyCoefficient)

        _questionUIState.value = _questionUIState.value?.copy(
            countRightAnswer = answer,
            setAnswer = true
        )
    }

    private fun updateStudyCoefficient(word: Word, studyCoefficient: Long) {
        val newWord = word.copy(studyCoefficient = studyCoefficient)

        viewModelScope.launch {
            dictionaryRepository.updateWord(newWord)
        }
    }
}
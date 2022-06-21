package com.example.task.wordsfactory.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task.wordsfactory.ui.DictionaryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor(
    private val dictionaryRepository: DictionaryRepository
)  : ViewModel() {
    private val _questionUIState = MutableLiveData(QuestionUIState())
    val questionUIState: LiveData<QuestionUIState> = _questionUIState

    init {
        viewModelScope.launch {
            dictionaryRepository.getTrainingWord().onSuccess {
                _questionUIState.value = _questionUIState.value?.copy(wordsForTraining = it)
            }
        }
    }
}
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
class TrainingViewModel @Inject constructor(
    private val dictionaryRepository: DictionaryRepository
) : ViewModel() {

    private val _trainingUIState = MutableLiveData(TrainingUIState())
    val trainingUIState: LiveData<TrainingUIState> = _trainingUIState

    init {
        getCountWord()
    }

    private fun getCountWord() = viewModelScope.launch {
        val count = dictionaryRepository.getCountWords()
        _trainingUIState.value = _trainingUIState.value?.copy(countWord = count)
    }

}
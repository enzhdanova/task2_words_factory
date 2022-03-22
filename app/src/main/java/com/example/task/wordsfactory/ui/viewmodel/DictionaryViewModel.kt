package com.example.task.wordsfactory.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.task.wordsfactory.data.DictionaryRepositoryImpl
import com.example.task.wordsfactory.ui.entity.Word

class DictionaryViewModel : ViewModel() {
    private val _dictionaryUiState = MutableLiveData<DictionaryUiState>()
    val dictionaryUiState: LiveData<DictionaryUiState>
        get() = _dictionaryUiState
    private val dictionaryRepository = DictionaryRepositoryImpl()

    fun getWord(searchWord: String) {
        val result: Result<Word> = dictionaryRepository.getWord(searchWord)

        if (result.isSuccess) {
            result.onSuccess {
                _dictionaryUiState.value = DictionaryUiState(word = it)
            }
        } else {
            result.onFailure {
                _dictionaryUiState.value = DictionaryUiState(word = null, error = true, errorMessage = it.message ?: "")
            }
        }
    }

}
package com.example.task.wordsfactory.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.task.wordsfactory.data.Repository.DictionaryRepositoryImpl
import com.example.task.wordsfactory.data.dataSource.RemoteDataSource
import com.example.task.wordsfactory.domain.GetWordUseCase
import com.example.task.wordsfactory.ui.entity.WordUI

class DictionaryViewModel : ViewModel() {
    private val _dictionaryUiState = MutableLiveData<DictionaryUiState>()
    val dictionaryUiState: LiveData<DictionaryUiState>
        get() = _dictionaryUiState
    private val getWordUseCase = GetWordUseCase(
        dictionaryRepositoryImpl = DictionaryRepositoryImpl(RemoteDataSource())
    )

    fun getWord(searchWord: String) {
        val result: Result<WordUI> = getWordUseCase.getWord(searchWord)

        if (result.isSuccess) {
            result.onSuccess {
                println(it)
                _dictionaryUiState.value = DictionaryUiState(word = it)
                println("uiState ${_dictionaryUiState.value}")
            }
        } else {
            result.onFailure {
                _dictionaryUiState.value =
                    DictionaryUiState(word = null, error = true, errorMessage = it.message ?: "")
            }
        }
    }

}
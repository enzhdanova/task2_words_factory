package com.example.task.wordsfactory.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task.wordsfactory.data.Repository.DictionaryRepositoryImpl
import com.example.task.wordsfactory.data.data_source.LocalDataSource
import com.example.task.wordsfactory.data.data_source.RemoteDataSource
import com.example.task.wordsfactory.data.model.Word
import kotlinx.coroutines.launch

class DictionaryViewModel : ViewModel() {
    private val _dictionaryUiState = MutableLiveData<DictionaryUiState>()
    val dictionaryUiState: LiveData<DictionaryUiState>
        get() = _dictionaryUiState
    private val dictionaryRepositoryImpl = DictionaryRepositoryImpl(RemoteDataSource(), LocalDataSource())

    fun getWord(searchWord: String) {

        viewModelScope.launch {

            val result: Result<Word> = dictionaryRepositoryImpl.getWord(searchWord)

            if (result.isSuccess) {
                result.onSuccess {
                    _dictionaryUiState.value = DictionaryUiState(word = it)
                }
            } else {
                result.onFailure {
                    _dictionaryUiState.value =
                        DictionaryUiState(
                            word = null,
                            error = true,
                            errorMessage = it.message ?: ""
                        )
                }
            }
        }
    }

}
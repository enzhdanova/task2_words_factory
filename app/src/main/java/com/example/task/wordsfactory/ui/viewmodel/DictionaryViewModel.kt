package com.example.task.wordsfactory.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task.wordsfactory.R
import com.example.task.wordsfactory.data.model.Word
import com.example.task.wordsfactory.ui.DictionaryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DictionaryViewModel @Inject constructor(
    private val dictionaryRepository: DictionaryRepository
) : ViewModel() {
    private val _dictionaryUiState = MutableLiveData<DictionaryUiState>()
    val dictionaryUiState: LiveData<DictionaryUiState>
        get() = _dictionaryUiState

    fun getWord(searchWord: String) {
        viewModelScope.launch {
            val result: Result<Word> = dictionaryRepository.getWord(searchWord)

            result.fold(
                onSuccess = {
                    _dictionaryUiState.value = DictionaryUiState(word = it)
                },
                onFailure = {
                    _dictionaryUiState.value =
                        DictionaryUiState(
                            word = null,
                            error = true,
                            errorMessage = R.string.network_error
                        )
                }
            )
        }
    }

    fun addWordToDictionary(word: Word) {
        viewModelScope.launch {
            dictionaryRepository.addToDictionary(word)
        }
    }
}
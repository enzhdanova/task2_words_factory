package com.example.task.wordsfactory.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task.wordsfactory.R
import com.example.task.wordsfactory.ui.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableLiveData<SplashUIState>()
    val uiState: LiveData<SplashUIState>
        get() = _uiState

    init {
        getUser()
    }

    fun getUser() {
        viewModelScope.launch {
            val result = authRepository.getUser()
            result.fold(
                onSuccess = {
                    _uiState.value = SplashUIState(
                        successLogin = true
                    )
                },
                onFailure = {
                    _uiState.value = SplashUIState(
                        error = true,
                        errorMessage = R.string.problem_with_get_data_error
                    )
                }
            )
        }
    }

    fun removeUser() {
        viewModelScope.launch {
            val result = authRepository.removeUser()
            result.onFailure {
                _uiState.value = SplashUIState(
                    error = true,
                    errorMessage = R.string.problem_with_get_data_error
                )
            }
        }
    }
}
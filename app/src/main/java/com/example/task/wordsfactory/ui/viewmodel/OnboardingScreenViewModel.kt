package com.example.task.wordsfactory.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OnBoardingScreenViewModel : ViewModel() {

    private val _uiStateLiveData = MutableLiveData<OnboardingUIState>()
    val uiStateLiveData: LiveData<OnboardingUIState>
        get() = _uiStateLiveData

    init {
        _uiStateLiveData.value = OnboardingUIState()
    }

    fun setNewPosition(newPosition: Int){
        _uiStateLiveData.value = _uiStateLiveData.value?.copy(currentPosition = newPosition)
    }

    fun getNextPosition(){
        var newPosition = _uiStateLiveData.value?.currentPosition ?: 0
        newPosition++
        setNewPosition(newPosition)
    }
}

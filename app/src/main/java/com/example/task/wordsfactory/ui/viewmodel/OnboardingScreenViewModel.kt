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

    fun onOnBoardingStepChanged(newPosition: Int) {
        _uiStateLiveData.value = OnboardingUIState(currentPosition = newPosition)
    }

    fun nextButtonOnClick(){
        var newPosition = _uiStateLiveData.value?.currentPosition ?: 0
        newPosition++
        _uiStateLiveData.value = _uiStateLiveData.value?.copy(currentPosition = newPosition)
    }


}

package com.example.task.wordsfactory.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.task.wordsfactory.data.OnboardingStep

class OnBoardingScreenViewModel : ViewModel() {

    val onboardingSteps = OnboardingStep.values().toList()
    private val _currentPosition = MutableLiveData<Int>()
    val currentPosition: LiveData<Int>
        get() = _currentPosition

    init {
        _currentPosition.value = 0
    }

    fun setNewPosition(newPosition: Int){
        _currentPosition.value = newPosition
    }

    fun getNextPosition(){
        var newPosition = currentPosition.value ?:0
        newPosition++
        setNewPosition(newPosition)
    }
}

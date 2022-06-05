package com.example.task.wordsfactory.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

//@HiltViewModel
class TrainingViewModel //Inject constructor()
 : ViewModel() {

    private val _trainingUIState = MutableLiveData<TrainingUIState>()
    val trainingUIState: LiveData<TrainingUIState> = _trainingUIState

    fun getCountWord() : Long = trainingUIState.value?.countWord ?: 0
}
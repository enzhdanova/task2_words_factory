package com.example.task.wordsfactory.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task.wordsfactory.data.InformationRepository
import com.example.task.wordsfactory.data.Result
import com.example.task.wordsfactory.data.model.Information
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class OnBoardingScreenViewModel @Inject constructor(
    private val informationRepository: InformationRepository
) : ViewModel() {

    private val _uiStateLiveData = MutableLiveData<OnBoardingScreenUIState>()
    val uiStateLiveData: LiveData<OnBoardingScreenUIState>
        get() = _uiStateLiveData


    fun fetchInfo(position: Int) {
         viewModelScope.launch {
            val result = informationRepository.getInfo(position)
            when (result) {
                is Result.Success<Information> ->
                    _uiStateLiveData.value = OnBoardingScreenUIState(
                        title = result.data.title,
                        subtitle = result.data.subtitle,
                        image = result.data.image,
                        error = false
                    )
                is Result.Error ->
                    _uiStateLiveData.value = OnBoardingScreenUIState(
                        error = true
                    )
            }
        }
    }
}

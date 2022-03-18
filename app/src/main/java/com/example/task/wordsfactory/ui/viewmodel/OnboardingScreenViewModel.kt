package com.example.task.wordsfactory.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.task.wordsfactory.data.InformationRepository
import com.example.task.wordsfactory.data.OnboardingInfoEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnBoardingScreenViewModel @Inject constructor(
    private val informationRepository: InformationRepository
) : ViewModel() {

    private val _uiStateLiveData = MutableLiveData<OnBoardingScreenUIState>()
    val uiStateLiveData: LiveData<OnBoardingScreenUIState>
        get() = _uiStateLiveData

    fun fetchInfo(position: Int) {
        println(uiStateLiveData.value)
        val result = OnboardingInfoEnum.values()[position]
        println(result)

        _uiStateLiveData.value = OnBoardingScreenUIState(title = result.title, subtitle = result.subtitle, image = result.image)
    //_uiStateLiveData.value?.copy(title = result.title, subtitle = result.subtitle, image = result.image)
    }
}

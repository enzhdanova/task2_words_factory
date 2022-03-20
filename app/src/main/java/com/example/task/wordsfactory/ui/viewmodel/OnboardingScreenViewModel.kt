package com.example.task.wordsfactory.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.viewpager2.widget.ViewPager2
import com.example.task.wordsfactory.data.OnboardingInfoEnum

class OnBoardingScreenViewModel : ViewModel() {

    private val _uiStateLiveData = MutableLiveData<OnBoardingScreenUIState>()
    val uiStateLiveData: LiveData<OnBoardingScreenUIState>
        get() = _uiStateLiveData

    init {
        fetchInfo()
    }

    private fun fetchInfo() {
        val result = OnboardingInfoEnum.values()

        val tmp: MutableList<OnboardingFragmentUIState> = mutableListOf()
        result.forEach {
            tmp.add(
                OnboardingFragmentUIState(
                    title = it.title,
                    subtitle = it.subtitle,
                    image = it.image
                )
            )
        }
        _uiStateLiveData.value = OnBoardingScreenUIState(
            fragmentUiStateList = tmp
        )
    }

    fun update(position: Int) {
        _uiStateLiveData.value = _uiStateLiveData.value?.copy(currentPosition = position)
    }

    fun nextButtonOnClick(viewPager: ViewPager2?) {
        if (viewPager != null) {
            if (viewPager.currentItem == OnboardingInfoEnum.values().size) {
                //TODO: Тут переход в другое активити
            } else {
                viewPager.currentItem++
            }
        }
    }

}

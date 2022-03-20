package com.example.task.wordsfactory.ui.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.viewpager2.widget.ViewPager2
import com.example.task.wordsfactory.R
import com.example.task.wordsfactory.data.OnboardingInfoEnum
import com.example.task.wordsfactory.ui.view.OnBoardingScreensActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.FieldPosition
import javax.inject.Inject

@HiltViewModel
class OnBoardingScreenViewModel @Inject constructor(
) : ViewModel() {

    private val _uiStateLiveData = MutableLiveData<OnBoardingScreenUIState>()
    val uiStateLiveData: LiveData<OnBoardingScreenUIState>
        get() = _uiStateLiveData

    init {
        fetchInfo()
    }

    fun fetchInfo() {
        println(uiStateLiveData.value)
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
            fragmentUiStateList = tmp,
            buttonText = OnboardingInfoEnum.One.buttonText
        )
        println(result)

    }

    fun update(position: Int) {
        _uiStateLiveData.value = _uiStateLiveData.value?.copy(buttonText = OnboardingInfoEnum.values()[position].buttonText)
    }

    fun updateTmp(viewPager: ViewPager2?, context: Context){
        if (viewPager != null) {
            if (viewPager?.currentItem == OnboardingInfoEnum.values().size) {
                //TODO: В будущем заменить Toast на переход в другое активити
                Toast.makeText(
                    context,
                    context.getString(R.string.todo_next_activity),
                    Toast.LENGTH_LONG
                )
                    .show()
            } else {
                viewPager?.let {
                    it.currentItem++
                }
            }
        }
    }

}

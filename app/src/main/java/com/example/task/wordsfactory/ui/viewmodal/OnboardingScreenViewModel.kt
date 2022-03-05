package com.example.task.wordsfactory.ui.viewmodal

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task.wordsfactory.R
import com.example.task.wordsfactory.data.InformationRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

class OnBoardingScreenViewModel(private val informationRepository: InformationRepository) : ViewModel(){

//    data class OnBoardingScreenState(
//        @StringRes val title: Int = R.string.learn,
//        @StringRes val subtitle: Int = R.string.subtitle,
//        @DrawableRes val image: Int = R.drawable.ic_cool_kids_long_distance_relationship
//    )

    private val _uiState = MutableStateFlow(OnBoardingScreenState())
    val uiState: StateFlow<OnBoardingScreenState> = _uiState.asStateFlow()

    private var fetchJob: Job? = null

    fun fetchInfo(position: Int) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            try {
                val inf = informationRepository.information[position]
                _uiState.update {
                    it.copy(title = inf.title, subtitle = inf.subtitle, image = inf.image)
                }
            } catch (ioe: IOException){
                println("IOException in fetchInfo(position: Int)")
            }
        }
    }

}

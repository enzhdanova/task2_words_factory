package com.example.task.wordsfactory.ui.viewmodal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task.wordsfactory.R
import com.example.task.wordsfactory.data.InformationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class OnBoardingScreenViewModel @Inject constructor(
    private val informationRepository: InformationRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(OnBoardingScreenState())
    val uiState: StateFlow<OnBoardingScreenState> = _uiState.asStateFlow()

    private var fetchJob: Job? = null

    fun fetchInfo(position: Int) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            try {
                val inf = informationRepository.getInfo(position)
                val buttonNextText = if (position == 2) R.string.next else R.string.start
                _uiState.update {
                    it.copy(
                        title = inf.title, subtitle = inf.subtitle,
                        image = inf.image
                    )
                }
            } catch (ioe: IOException) {
            }
        }
    }

}

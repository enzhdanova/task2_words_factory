package com.example.task.wordsfactory.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.task.wordsfactory.databinding.ActivityTrainingBinding
import com.example.task.wordsfactory.ui.viewmodel.TrainingViewModel
import dagger.hilt.android.AndroidEntryPoint

//@AndroidEntryPoint
class TrainingActivity : AppCompatActivity() {

    private var binding: ActivityTrainingBinding? = null
  //  private val viewModel by viewModels<TrainingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrainingBinding.inflate(layoutInflater)
        val view = binding?.root
        setContentView(view)

        initView()

    }

    private fun initView(){
      binding?.textviewCountword?.text = "0"//viewModel.trainingUIState.value?.countWord.toString()
    }
}
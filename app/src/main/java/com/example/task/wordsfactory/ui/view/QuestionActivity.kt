package com.example.task.wordsfactory.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.viewModelScope
import com.example.task.wordsfactory.R
import com.example.task.wordsfactory.databinding.ActivityQuestionBinding
import com.example.task.wordsfactory.ui.viewmodel.QuestionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuestionActivity : AppCompatActivity() {

    private var binding: ActivityQuestionBinding? = null
    private val viewModel by viewModels<QuestionViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        initView()

        viewModel.questionUIState.observe(this){
            uiState ->

            val countWord = uiState.countQuestion
            val nowQuestion = uiState.numberQuestion
            binding?.countQuestion?.text = "$nowQuestion ${getString(R.string.of)} $countWord"
            binding?.meaningWord?.text = uiState.nowQuestion
            binding?.answer1?.text = uiState.answer1
            binding?.answer2?.text = uiState.answer2
            binding?.answer3?.text = uiState.answer3
        }
    }

    private fun initView() {
        binding?.layoutAnswer1?.setOnClickListener(questionListener)
        binding?.layoutAnswer2?.setOnClickListener(questionListener)
        binding?.layoutAnswer3?.setOnClickListener(questionListener)

    }

    private val questionListener = View.OnClickListener { viewModel.getQuestion() }
}
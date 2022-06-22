package com.example.task.wordsfactory.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.task.wordsfactory.R
import com.example.task.wordsfactory.databinding.ActivityQuestionBinding
import com.example.task.wordsfactory.ui.viewmodel.QuestionUIState
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


            if (uiState.countQuestions < uiState.numberNowQuestion) {
                println("MyApp: finish ")
                Toast.makeText(this, "Тут все", Toast.LENGTH_LONG).show()
            } else {
                setQuestion(uiState)
            }
        }
    }

    private fun initView() {
        binding?.layoutAnswer1?.setOnClickListener(answerListener)
        binding?.layoutAnswer2?.setOnClickListener(answerListener)
        binding?.layoutAnswer3?.setOnClickListener(answerListener)

    }

    private val answerListener = View.OnClickListener { viewModel.getQuestion() }

    private fun setQuestion(uiState: QuestionUIState){
        val countWord = uiState.countQuestions
        val nowQuestion = uiState.numberNowQuestion
        binding?.countQuestion?.text = "$nowQuestion ${getString(R.string.of)} $countWord"
        binding?.meaningWord?.text = uiState.nowQuestion
        binding?.answer1?.text = uiState.answer1
        binding?.answer2?.text = uiState.answer2
        binding?.answer3?.text = uiState.answer3
    }
}
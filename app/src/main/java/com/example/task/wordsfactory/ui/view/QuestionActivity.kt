package com.example.task.wordsfactory.ui.view

import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.animation.doOnEnd
import com.example.task.wordsfactory.R
import com.example.task.wordsfactory.databinding.ActivityQuestionBinding
import com.example.task.wordsfactory.ui.viewmodel.QuestionUIState
import com.example.task.wordsfactory.ui.viewmodel.QuestionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuestionActivity : AppCompatActivity() {

    private var binding: ActivityQuestionBinding? = null
    private val viewModel by viewModels<QuestionViewModel>()
    private var animator: ValueAnimator? = null

    companion object {
        private const val START_PROGRESS = 0
        private const val END_PROGRESS = 100
        private const val DURATION_TIMER = 5000L
        private const val UNSELECTED_ANSWER = -1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        initView()

        viewModel.questionUIState.observe(this) { uiState ->

            if (uiState.countQuestions < uiState.numberNowQuestion) {
                println("MyApp: finish")
                Toast.makeText(this, "Тут все", Toast.LENGTH_LONG).show()
            }

            if (!uiState.setAnswer)
                setQuestion(uiState)
        }
    }

    private val timer = object : CountDownTimer(200, 1) {
        override fun onTick(millisUntilFinished: Long) {}

        override fun onFinish() {
            viewModel.getQuestion()
        }
    }

    private fun initView() {
        val answersButton = mutableListOf(
            binding?.layoutAnswer1,
            binding?.layoutAnswer2,
            binding?.layoutAnswer3
        )

        answersButton.forEachIndexed { index, constraintLayout ->
            constraintLayout?.setOnClickListener {

                val backgroundColor = if (viewModel.isRightAnswer(index)) {
                    R.drawable.round_background_right
                } else {
                    R.drawable.round_background_mistake
                }
                it.background = getDrawable(backgroundColor)
                viewModel.setAnswer(index)
                animator?.pause()
                timer.start()

            }
        }
    }


    private fun setQuestion(uiState: QuestionUIState) {
        val countWord = uiState.countQuestions
        val nowQuestion = uiState.numberNowQuestion
        binding?.countQuestion?.text = "$nowQuestion ${getString(R.string.of)} $countWord"
        binding?.meaningWord?.text = uiState.nowQuestion
        println(uiState)
        if (uiState.answer.size == 3) {
            binding?.answer1?.text = uiState.answer[0]
            binding?.layoutAnswer1?.background = getDrawable(R.drawable.round_background)
            binding?.answer2?.text = uiState.answer[1]
            binding?.layoutAnswer2?.background = getDrawable(R.drawable.round_background)
            binding?.answer3?.text = uiState.answer[2]
            binding?.layoutAnswer3?.background = getDrawable(R.drawable.round_background)
        }

        startProgress()
    }

    private fun startProgress() {
        animator = ValueAnimator.ofInt(START_PROGRESS, END_PROGRESS).apply {
            duration = DURATION_TIMER
            addUpdateListener {
                binding?.progress?.progress = it.animatedValue as Int
            }
            start()

            doOnEnd {
                println("MyApp: время окончено")
                viewModel.setAnswer(UNSELECTED_ANSWER)
                viewModel.getQuestion()

            }
        }


    }

}
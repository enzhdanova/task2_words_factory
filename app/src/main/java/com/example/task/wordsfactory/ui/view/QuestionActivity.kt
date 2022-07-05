package com.example.task.wordsfactory.ui.view

import android.animation.ValueAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
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
        private const val TIMER_SHOW_BUTTON = 200L
        private const val TIMER_INTERVAL = 1L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        initView()

        viewModel.questionUIState.observe(this) { uiState ->
            if (uiState.countQuestions < uiState.numberNowQuestion) {
                startFinishActivity(
                    correct = uiState.countRightAnswer,
                    incorrect = uiState.countQuestions - uiState.countRightAnswer
                )
            }

            if (!uiState.setAnswer)
                setQuestion(uiState)
        }
    }

    private val timer = object : CountDownTimer(TIMER_SHOW_BUTTON, TIMER_INTERVAL) {
        override fun onTick(millisUntilFinished: Long) = Unit

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

                timer.start()
            }
        }
    }


    private fun setQuestion(uiState: QuestionUIState) {
        val countWord = uiState.countQuestions
        val nowQuestion = uiState.numberNowQuestion
        binding?.countQuestion?.text = "$nowQuestion ${getString(R.string.of)} $countWord"
        binding?.meaningWord?.text = uiState.nowQuestion

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
        animator?.pause()
        animator?.removeAllUpdateListeners()

        animator = ValueAnimator.ofInt(START_PROGRESS, END_PROGRESS).apply {
            duration = DURATION_TIMER

            addUpdateListener {
                binding?.progress?.progress = it.animatedValue as Int
            }
            start()

            doOnEnd {
                if (viewModel.questionUIState.value?.setAnswer == false) {
                    viewModel.setAnswer(UNSELECTED_ANSWER)
                    viewModel.getQuestion()
                }
            }
        }
    }

    private fun startFinishActivity(correct: Int, incorrect: Int) {
        val intent = Intent(this, TrainingFinishActivity::class.java)
        intent.putExtra(TrainingFinishActivity.CORRECT_ARG, correct)
        intent.putExtra(TrainingFinishActivity.INCORRECT_ARG, incorrect)
        startActivity(intent)
        finish()
    }
}
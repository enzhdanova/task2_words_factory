package com.example.task.wordsfactory.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.task.wordsfactory.databinding.ActivityTrainingFinishBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrainingFinishActivity : AppCompatActivity() {

    private var binding: ActivityTrainingFinishBinding? = null

    companion object {
        const val CORRECT_ARG = "CORRECT"
        const val INCORRECT_ARG = "INCORRECT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrainingFinishBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        initView()
    }

    private fun initView() {
        val arguments = intent.extras
        arguments?.apply {
            binding?.correctValue?.text = get(CORRECT_ARG).toString()
            binding?.incorrectValue?.text = get(INCORRECT_ARG).toString()
        }

        binding?.buttonAgain?.setOnClickListener {
            val intent = Intent(this, QuestionActivity::class.java)
            startActivity(intent)
        }
    }
}
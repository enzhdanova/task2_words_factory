package com.example.task.wordsfactory.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.task.wordsfactory.data.OnboardingStep
import com.example.task.wordsfactory.databinding.ActivityOnBoardingScreensBinding
import com.example.task.wordsfactory.ui.viewmodel.OnBoardingScreenViewModel

class OnBoardingScreensActivity : AppCompatActivity() {

    val viewModel by viewModels<OnBoardingScreenViewModel>()

    private var demoCollectionAdapter: OnBoardingScreenCollectionAdapter? = null
    private var binding: ActivityOnBoardingScreensBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingScreensBinding.inflate(layoutInflater)
        val view = binding?.root
        setContentView(view)


        demoCollectionAdapter =
            viewModel.uiStateLiveData.value?.let { OnBoardingScreenCollectionAdapter(this) }
        binding?.viewpager?.adapter = demoCollectionAdapter

        binding?.buttonNext?.setOnClickListener {
            viewModel.nextButtonOnClick(binding?.viewpager)
        }

        binding?.buttonSkip?.setOnClickListener {
            //TODO: В будущем переход в другое активити
        }

        viewModel.uiStateLiveData.observe(this) {
            binding?.buttonNext?.setText(OnboardingStep.values()[it.currentPosition].buttonText)
        }

    }

}

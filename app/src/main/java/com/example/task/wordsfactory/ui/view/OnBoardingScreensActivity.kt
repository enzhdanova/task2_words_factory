package com.example.task.wordsfactory.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.viewModels
import com.example.task.wordsfactory.R
import com.example.task.wordsfactory.data.OnboardingStep
import com.example.task.wordsfactory.databinding.ActivityOnBoardingScreensBinding
import com.example.task.wordsfactory.ui.viewmodel.OnBoardingScreenViewModel

class OnBoardingScreensActivity : AppCompatActivity() {

    private val viewModel by viewModels<OnBoardingScreenViewModel>()

    private var demoCollectionAdapter: OnBoardingScreenCollectionAdapter? = null
    private var binding: ActivityOnBoardingScreensBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingScreensBinding.inflate(layoutInflater)
        val view = binding?.root
        setContentView(view)

        demoCollectionAdapter = OnBoardingScreenCollectionAdapter(this)
        binding?.viewpager?.adapter = demoCollectionAdapter

        val imageViewPositionList = listOf(
            binding?.position1,
            binding?.position2,
            binding?.position3,
        )

        binding?.buttonNext?.setOnClickListener {
            viewModel.nextButtonOnClick()
        }

        binding?.buttonSkip?.setOnClickListener {
            //TODO: В будущем переход в другое активити
        }

        viewModel.uiStateLiveData.observe(this) {
            val maxSize = OnboardingStep.values().size
            if (it.currentPosition < maxSize) {
                binding?.viewpager?.currentItem = it.currentPosition
                binding?.buttonNext?.setText(OnboardingStep.values()[it.currentPosition].buttonText)
                for (i in 0 until maxSize)
                    imageViewPositionList[i]?.setImageResource(R.drawable.ic_tab)
                imageViewPositionList[it.currentPosition]?.setImageResource(R.drawable.ic_current)
            } else {
                //TODO: переход в другое активити
            }
        }
    }
}


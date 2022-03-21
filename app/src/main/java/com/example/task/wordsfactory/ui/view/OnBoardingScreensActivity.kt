package com.example.task.wordsfactory.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
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

        demoCollectionAdapter = OnBoardingScreenCollectionAdapter(this, viewModel.onboardingSteps)
        binding?.viewpager?.adapter = demoCollectionAdapter
        binding?.viewpager?.let {
            binding?.dotsIndicator?.setViewPager2(viewPager2 = it)
        }

        binding?.buttonNext?.setOnClickListener {
            viewModel.getNextPosition()
        }

        binding?.buttonSkip?.setOnClickListener {
            //TODO: В будущем переход в другое активити
        }

        viewModel.currentPosition.observe(this) { currentPosition ->
            val maxSize = OnboardingStep.values().size
            if (currentPosition < maxSize) {
                binding?.viewpager?.currentItem = currentPosition
                binding?.buttonNext?.setText(OnboardingStep.values()[currentPosition].buttonText)
//                for (i in 0 until maxSize)
//                    imageViewPositionList[i]?.setImageResource(R.drawable.ic_tab)
//                imageViewPositionList[currentPosition]?.setImageResource(R.drawable.ic_current)
            } else {
                //TODO: переход в другое активити
            }
        }

        binding?.viewpager?.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                viewModel.setNewPosition(position)
            }
        })
    }
}
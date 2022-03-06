package com.example.task.wordsfactory.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewpager2.widget.ViewPager2
import com.example.task.wordsfactory.R
import com.example.task.wordsfactory.ui.viewmodal.OnBoardingScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OnBoardingScreensActivity : AppCompatActivity() {

    private lateinit var demoCollectionAdapter: DemoCollectionAdapter
    protected lateinit var viewPager: ViewPager2
    val viewModel by viewModels<OnBoardingScreenViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_screens)

        viewPager = findViewById(R.id.activity_onboarding_screens_viewpager)

        demoCollectionAdapter = DemoCollectionAdapter(this)
        viewPager.adapter = demoCollectionAdapter


    }
}

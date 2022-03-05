package com.example.task.wordsfactory.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewpager2.widget.ViewPager2
import com.example.task.wordsfactory.R
import com.example.task.wordsfactory.ui.viewmodal.OnBoardingScreenViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class OnBoardingScreensActivity : AppCompatActivity() {

    private lateinit var demoCollectionAdapter: DemoCollectionAdapter
    protected lateinit var viewPager: ViewPager2

    lateinit var viewModel: OnBoardingScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_screens)

        viewPager = findViewById(R.id.activity_onboarding_screens_viewpager)

        demoCollectionAdapter = DemoCollectionAdapter(this)
        viewPager.adapter = demoCollectionAdapter


    }
}

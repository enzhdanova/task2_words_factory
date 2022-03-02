package com.example.task.wordsfactory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2

class OnBoardingScreensActivity : AppCompatActivity() {

    private lateinit var demoCollectionAdapter: DemoCollectionAdapter
    protected lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_screens)

        viewPager = findViewById(R.id.activity_onboarding_screens_viewpager)

        demoCollectionAdapter = DemoCollectionAdapter(this)
        viewPager.adapter = demoCollectionAdapter
    }
}

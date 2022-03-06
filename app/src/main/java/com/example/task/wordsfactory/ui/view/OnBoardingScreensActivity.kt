package com.example.task.wordsfactory.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.task.wordsfactory.R
import com.example.task.wordsfactory.ui.viewmodal.OnBoardingScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingScreensActivity : AppCompatActivity() {

    private lateinit var demoCollectionAdapter: OnBoardingScreenCollectionAdapter
    protected lateinit var viewPager: ViewPager2
    val viewModel by viewModels<OnBoardingScreenViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_screens)
        viewPager = findViewById(R.id.activity_onboarding_screens_viewpager)
        val buttonNext = findViewById<Button>(R.id.activity_onboarding_screens_button_next)
        val skip = findViewById<Button>(R.id.activity_onboarding_screens_button_skip)

        demoCollectionAdapter = OnBoardingScreenCollectionAdapter(this)
        viewPager.adapter = demoCollectionAdapter

        buttonNext.setOnClickListener {
            if (viewPager.currentItem == 2){
                Toast.makeText(this, "Дальше нельзя", Toast.LENGTH_LONG).show()

            } else {
                viewPager.currentItem = viewPager.currentItem + 1
            }
        }

        skip.setOnClickListener {
            Toast.makeText(this, "Тут будет переход дальше", Toast.LENGTH_LONG).show()
            println("skip")
        }

    }
}

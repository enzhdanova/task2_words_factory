package com.example.task.wordsfactory.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.task.wordsfactory.R
import com.example.task.wordsfactory.ui.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {

    private val viewModel by viewModels<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        viewModel.uiState.observe(this){

            println("MyApp: $it")

            if (it.successLogin && it.name.isNotEmpty()){
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
                finish()
            }

            if (it.error){
                val intent = Intent(this, OnBoardingScreensActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

    }
}
package com.example.task.wordsfactory.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.task.wordsfactory.databinding.ActivityTrainingFinishBinding

class TrainingFinishActivity : AppCompatActivity() {

    private var binding: ActivityTrainingFinishBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrainingFinishBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }
}
package com.example.task.wordsfactory.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.task.wordsfactory.R
import com.example.task.wordsfactory.data.OnboardingInfoEnum
import com.example.task.wordsfactory.databinding.ActivityOnBoardingScreensBinding
import com.example.task.wordsfactory.ui.viewmodel.OnBoardingScreenUIState
import com.example.task.wordsfactory.ui.viewmodel.OnBoardingScreenViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
            viewModel.uiStateLiveData.value?.let { OnBoardingScreenCollectionAdapter(this, it.fragmentUiStateList) }
        binding?.viewpager?.adapter = demoCollectionAdapter

       // binding?.viewpager?.



        binding?.buttonNext?.setOnClickListener {
            viewModel.updateTmp(binding?.viewpager, this)
        }

        binding?.buttonSkip?.setOnClickListener {
            //TODO: В будущем заменить Toast на переход в другое активити
            Toast.makeText(this, getString(R.string.todo_next_activity), Toast.LENGTH_LONG).show()
        }


        viewModel.uiStateLiveData.observe(this){
            binding?.buttonNext?.setText(it.buttonText)
        }
    }

}

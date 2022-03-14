package com.example.task.wordsfactory.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.example.task.wordsfactory.R
import com.example.task.wordsfactory.databinding.ActivityOnBoardingScreensBinding
import com.example.task.wordsfactory.ui.viewmodel.OnBoardingScreenViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingScreensActivity : AppCompatActivity() {

    companion object {
        private const val COUNT_FRAGMENT = 3
    }

    private var demoCollectionAdapter: OnBoardingScreenCollectionAdapter? = null
    private var binding: ActivityOnBoardingScreensBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingScreensBinding.inflate(layoutInflater)
        val view = binding?.root
        setContentView(view)

        demoCollectionAdapter = OnBoardingScreenCollectionAdapter(COUNT_FRAGMENT, this)
        binding?.viewpager?.adapter = demoCollectionAdapter

        binding?.buttonNext?.setOnClickListener{
            if (binding?.viewpager?.currentItem == COUNT_FRAGMENT - 1) {
                //TODO: В будущем заменить Toast на переход в другое активити
                Toast.makeText(this, getString(R.string.todo_next_activity), Toast.LENGTH_LONG).show()
            } else {
                binding?.viewpager?.let {
                    it.currentItem++
                }
            }
        }

        binding?.buttonSkip?.setOnClickListener {
            //TODO: В будущем заменить Toast на переход в другое активити
            Toast.makeText(this, getString(R.string.todo_next_activity), Toast.LENGTH_LONG).show()
            println("skip")
        }
    }

}

package com.example.task.wordsfactory.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.task.wordsfactory.databinding.ActivitySignUpScreenBinding
import com.example.task.wordsfactory.ui.viewmodel.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpScreenActivity : AppCompatActivity() {

    private val viewModel by viewModels<SignUpViewModel>()
    private var binding: ActivitySignUpScreenBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpScreenBinding.inflate(layoutInflater)
        val view = binding?.root

        setContentView(view)

        binding?.signUpButton?.setOnClickListener(buttonSignUpOnClick)

        viewModel.uiState.observe(this) { uiState ->
            if (uiState.error) {
                ErrorDialogFragment
                    .createDialog(getString(uiState.errorMessage))
                    .show(supportFragmentManager, ErrorDialogFragment.ERROR_TAG)
            }
            if (uiState.successLogin) {
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private val buttonSignUpOnClick = View.OnClickListener {
        val name = binding?.nameEdittext?.text.toString()
        val email = binding?.eMailEdittext?.text.toString()
        val password = binding?.passwordEdittext?.text.toString()
        viewModel.login(name, email, password)
    }
}
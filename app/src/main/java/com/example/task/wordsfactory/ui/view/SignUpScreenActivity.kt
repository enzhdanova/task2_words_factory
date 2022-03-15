package com.example.task.wordsfactory.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import com.example.task.wordsfactory.R
import com.example.task.wordsfactory.databinding.ActivitySignUpScreenBinding
import com.example.task.wordsfactory.ui.viewmodel.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint

/*
* TODO:При нажатии на кнопку "Sign Up" необходимо проверять поля для ввода на пустоту.
*  При некорректном заполнении необходимо отобразить ошибку с помощью диалогового окна.
*  При наличии всех данных необходимо перейти на следующий экран.
* */

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
    }

    private val buttonSignUpOnClick = View.OnClickListener {
        val name = binding?.nameEdittext?.text.toString()
        val email = binding?.eMailEdittext?.text.toString()
        val password = binding?.passwordEdittext?.text.toString()
        viewModel.login(name, email, password)
        val userLogin = viewModel.getUser()
        System.out.println("getSharedPreferences $userLogin")
    }

}
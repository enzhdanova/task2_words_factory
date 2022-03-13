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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_screen)

        val nameEditText = findViewById<EditText>(R.id.activity_sign_up_name_edittext)
        val emailEditText = findViewById<EditText>(R.id.activity_sign_up_e_mail_edittext)
        val passwordEditText = findViewById<EditText>(R.id.activity_sign_up_password_edittext)
        val signUpButton = findViewById<Button>(R.id.activity_sign_up_button)

        nameEditText.addTextChangedListener(textWatcherName)
        emailEditText.addTextChangedListener(textWatcherEmail)
        passwordEditText.addTextChangedListener(textWatcherPassword)
        signUpButton.setOnClickListener(buttonSignUpOnClick)


      /*  val authRepositoryImpl: AuthRepositoryImpl = AuthRepositoryImpl(application)
        authRepositoryImpl.login("1","2","3")

        val sP = application.getSharedPreferences("preference", Context.MODE_PRIVATE)
        val tmp = sP.getString("example", "0")
        System.out.println("getSharedPreferences $tmp")
*/

       /* lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.uiState.collect {
                }

            }
        }*/
    }

    private val textWatcherName = object : TextWatcher{
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            viewModel.editTextNameChanged(p0.toString())
        }

        override fun afterTextChanged(p0: Editable?) {}
    }

    private val textWatcherEmail = object : TextWatcher{
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            viewModel.editTextEmailChanged(p0.toString())
        }

        override fun afterTextChanged(p0: Editable?) {}
    }

    private val textWatcherPassword = object : TextWatcher{
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            viewModel.editTextPasswordChanged(p0.toString())
        }

        override fun afterTextChanged(p0: Editable?) {}
    }

    private val buttonSignUpOnClick = View.OnClickListener {
        viewModel.login()
        val userLogin = viewModel.getUser()
        System.out.println("getSharedPreferences $userLogin")
    }



}
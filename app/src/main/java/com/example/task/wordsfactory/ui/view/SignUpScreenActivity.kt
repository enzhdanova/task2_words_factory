package com.example.task.wordsfactory.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.task.wordsfactory.R
import com.example.task.wordsfactory.ui.viewmodel.SignUpViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class SignUpScreenActivity : AppCompatActivity() {

    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_screen)

        val nameEditText = findViewById<EditText>(R.id.activity_sign_up_name_edittext)
        val emailEditText = findViewById<EditText>(R.id.activity_sign_up_e_mail_edittext)
        val passwordEditText = findViewById<EditText>(R.id.activity_sign_up_password_edittext)

        nameEditText.addTextChangedListener(textWatcherName)
        emailEditText.addTextChangedListener(textWatcherEmail)
        passwordEditText.addTextChangedListener(textWatcherPassword)


        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.uiState.collect {
                }

                   /* .collect {
                   // emailEditText.setText(it.e_mail)
                    System.out.println("________________start________________________________________")
                    System.out.println("in lifecycleScope: $it")
                    System.out.println("it.passwordVisible ${it.passwordVisible}")
                    System.out.println("it.visibleType ${it.visibleType}    inputTypePassword ${InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD}")


                    passwordEditText.inputType = it.visibleType //.inputType = it.visibleType
                    System.out.println("passwordEditText.inputType ${passwordEditText.inputType}")
                    visiblePasswordButton.setImageResource(it.passwordVisibleImage)
                    System.out.println("________________end________________________________________")
                }*/
            }
        }
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




}
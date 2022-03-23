package com.example.task.wordsfactory.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.navigation.fragment.NavHostFragment
import com.example.task.wordsfactory.R
import com.example.task.wordsfactory.databinding.ActivityDictionaryBinding
import com.example.task.wordsfactory.ui.viewmodel.DictionaryViewModel

class DictionaryActivity : AppCompatActivity() {
    private var binding: ActivityDictionaryBinding? = null
    private val viewModel by viewModels<DictionaryViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDictionaryBinding.inflate(layoutInflater)
        val view = binding?.root
        setContentView(view)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.word_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding?.searchButton?.setOnClickListener {
            viewModel.getWord(binding?.searchEdittext?.text.toString())
        }

        viewModel.dictionaryUiState.observe(this) {
            if (it.error) {
                Toast.makeText(this, "Ошибка", Toast.LENGTH_LONG).show()
            } else {
           /*     val bundle = bundleOf(
                    "word" to it.word?.word,
                    "phonetic" to it.word?.phonetic,
                )
                navController.navigate(R.id.action_search_word, bundle)
*/
                val action = WordsFragmentDirections.actionSearchWord(it.word?.word?:"", it.word?.phonetic?:"")
                navController.navigate(action)
            }
        }








    }
}
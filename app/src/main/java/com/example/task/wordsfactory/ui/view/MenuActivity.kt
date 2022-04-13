package com.example.task.wordsfactory.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.commit
import com.example.task.wordsfactory.R
import com.example.task.wordsfactory.databinding.ActivityMenuBinding
import com.google.android.material.navigation.NavigationBarView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuActivity : AppCompatActivity() {

    private var binding : ActivityMenuBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuBinding.inflate(layoutInflater)
        val view = binding?.root
        setContentView(view)

        binding?.menu?.selectedItemId?.let { setNowFragment(it) }

        binding?.menu?.setOnItemSelectedListener {
            println("MyApp: "+it.itemId)
            setNowFragment(it.itemId)
            true
        }
    }

    fun setNowFragment(@IdRes itemId: Int) {
        when(itemId) {
            R.id.dictionary -> {
                supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace(R.id.wordfactory_fragment, DictionaryFragment())
                    addToBackStack(DictionaryFragment.TAG)
                }
            }
            R.id.training -> {
                println("MyApp: вкладка тренировка")
            }
            R.id.video -> {
                println("MyApp: вкладка видио")
            }
        }
    }
}
package com.example.task.wordsfactory.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.task.wordsfactory.R
import com.example.task.wordsfactory.databinding.FragmentWordsBinding

class WordsFragment : Fragment() {

    private var binding: FragmentWordsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println(arguments)
    }

    /*
* "word" to it.word?.word,
                    "phonetic" to it.word?.phonetic
* */

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWordsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       /* arguments?.let {
            println(it)
            binding?.textviewWord?.text = it.getString("word")
            println(binding?.textviewWord?.text)
            binding?.textviewWordTranscription?.text = it.getString("phonetic")
        }*/

        val args: WordsFragmentArgs by navArgs()
        val word = args.word
        val ph = args.phonetic
        binding?.textviewWord?.text = word
        println(binding?.textviewWord?.text)
        binding?.textviewWordTranscription?.text = ph
    }

    companion object {

        fun newInstance(param1: String, param2: String) =
            WordsFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
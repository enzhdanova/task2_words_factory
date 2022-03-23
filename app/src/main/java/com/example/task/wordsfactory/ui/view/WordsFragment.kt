package com.example.task.wordsfactory.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.task.wordsfactory.databinding.FragmentWordsBinding

class WordsFragment : Fragment() {

    private var binding: FragmentWordsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println(arguments)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWordsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: WordsFragmentArgs by navArgs()
        val word = args.resultWord
        binding?.textviewWord?.text = word.word
        println(binding?.textviewWord?.text)
        binding?.textviewWordTranscription?.text = word.phonetic
        binding?.textviewPartOfSpeechValue?.text = word.meanings?.get(0)?.partOfSpeech
    }
}
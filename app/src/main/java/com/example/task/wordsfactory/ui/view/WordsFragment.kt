package com.example.task.wordsfactory.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.room.Room
import com.example.task.wordsfactory.database.AppDatabase
import com.example.task.wordsfactory.database.entity.MeaningBD
import com.example.task.wordsfactory.database.entity.WordBD
import com.example.task.wordsfactory.databinding.FragmentWordsBinding
import com.example.task.wordsfactory.ui.Utils.MeaningWordItemDecoration
import com.example.task.wordsfactory.ui.Utils.WordMeaningAdapter
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WordsFragment : Fragment() {

    private var binding : FragmentWordsBinding? = null
    private val wordMeaningAdapter = WordMeaningAdapter()

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

        binding?.meaningRecycler?.apply {
            adapter = wordMeaningAdapter
            addItemDecoration(MeaningWordItemDecoration())
        }

        val wordArg = args.word

        with(wordArg) {
            binding?.textviewWord?.text = word
            binding?.textviewWordTranscription?.text = phonetic
            binding?.textviewPartOfSpeechValue?.text = partOfSpeech
            wordMeaningAdapter.submitList(meanings)
        }

        binding?.buttonAddToDict?.setOnClickListener {

        }



    }
}
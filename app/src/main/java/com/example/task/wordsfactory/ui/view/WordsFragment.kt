package com.example.task.wordsfactory.ui.view

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.task.wordsfactory.databinding.FragmentWordsBinding
import com.example.task.wordsfactory.ui.utils.MeaningWordItemDecoration
import com.example.task.wordsfactory.ui.utils.WordMeaningAdapter
import com.example.task.wordsfactory.ui.viewmodel.WordViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WordsFragment : Fragment() {

    private var binding : FragmentWordsBinding? = null
    private val wordMeaningAdapter = WordMeaningAdapter()
    private val viewModel by viewModels<WordViewModel>()

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

        val mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
        }

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
            if (audio == null) binding?.buttonSound?.visibility = View.GONE
        }

        binding?.buttonAddToDict?.setOnClickListener {
            viewModel.addWordToDictionary(wordArg)
        }

        binding?.buttonSound?.setOnClickListener {
            if (wordArg.audio != null) {
                mediaPlayer.apply {
                    setDataSource(wordArg.audio)
                    prepare()
                    start()
                }
            }
        }
    }
}
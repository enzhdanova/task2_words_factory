package com.example.task.wordsfactory.ui.view

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.task.wordsfactory.data.model.Word
import com.example.task.wordsfactory.databinding.DictionaryFragmentBinding
import com.example.task.wordsfactory.ui.utils.MeaningWordItemDecoration
import com.example.task.wordsfactory.ui.utils.WordMeaningAdapter
import com.example.task.wordsfactory.ui.viewmodel.DictionaryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DictionaryFragment : Fragment() {

    companion object {
        val TAG: String = DictionaryFragment::class.java.simpleName

        fun newInstance(): Fragment {
            return DictionaryFragment()
        }
    }

    private var binding: DictionaryFragmentBinding? = null
    private val wordMeaningAdapter = WordMeaningAdapter()
    private val viewModel by viewModels<DictionaryViewModel>()
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DictionaryFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        viewModel.dictionaryUiState.observe(viewLifecycleOwner) { uiState ->
            if (uiState.error) {
                ErrorDialogFragment.createDialog(getString(uiState.errorMessage))
                    .show(parentFragmentManager, ErrorDialogFragment.ERROR_TAG)
            }

            uiState.word?.let { word ->
                binding?.groupNoWord?.visibility = View.GONE
                binding?.groupWord?.visibility = View.VISIBLE
                setWord(word)
            }

        }
    }

    private fun setWord(word: Word) {
        binding?.textviewWord?.text = word.word
        binding?.textviewWordTranscription?.text = word.phonetic
        binding?.textviewPartOfSpeechValue?.text = word.partOfSpeech
        wordMeaningAdapter.submitList(word.meanings)
        if (word.audio == null) binding?.buttonSound?.visibility = View.GONE

    }

    private fun initView() {
        binding?.groupWord?.visibility = View.GONE

        mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
        }

        binding?.meaningRecycler?.apply {
            adapter = wordMeaningAdapter
            addItemDecoration(MeaningWordItemDecoration(context))
        }

        binding?.searchButton?.setOnClickListener {
            viewModel.getWord(binding?.searchEdittext?.text.toString())
        }


        binding?.buttonSound?.setOnClickListener {
            mediaPlayer?.apply {
                reset()
                setDataSource(viewModel.dictionaryUiState.value?.word?.audio)
                prepare()
                start()
            }
        }

        binding?.buttonAddToDict?.setOnClickListener {
            viewModel.addWordToDictionary()
        }
    }
}
package com.example.task.wordsfactory.ui.view

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.task.wordsfactory.R
import com.example.task.wordsfactory.databinding.FragmentTrainingBinding
import com.example.task.wordsfactory.ui.viewmodel.TrainingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrainingFragment : Fragment() {

    private var binding: FragmentTrainingBinding? = null
    private val viewModel by viewModels<TrainingViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTrainingBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val start = getString(R.string.there_are)
        val count = viewModel.trainingUIState.value?.countWord.toString()
        val end = getString(R.string.count_words_end)
        val spannable = SpannableString(
            "$start " +
                    "$count $end"
        )

        spannable.setSpan(
            ForegroundColorSpan(requireContext().getColor(R.color.orange)),
            start.length.plus(1),
            start.length.plus(count.length + 1), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )


        binding?.textviewCountword?.text = spannable
    }

    companion object {
        val TAG: String = TrainingFragment::class.java.simpleName

        fun newInstance() = TrainingFragment()
    }
}
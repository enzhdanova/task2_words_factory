package com.example.task.wordsfactory.ui.view

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.viewModels
import com.example.task.wordsfactory.R
import com.example.task.wordsfactory.databinding.FragmentTrainingBinding
import com.example.task.wordsfactory.ui.viewmodel.TrainingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrainingFragment : Fragment() {

    private var binding: FragmentTrainingBinding? = null
    private val viewModel by viewModels<TrainingViewModel>()

    companion object {
        val TAG: String = TrainingFragment::class.java.simpleName

        fun newInstance() = TrainingFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTrainingBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        viewModel.trainingUIState.observe(viewLifecycleOwner) {
            if (it.countWord == 0L) {
                binding?.textviewCountword?.text = getString(R.string.add_word_in_dictionary)
                binding?.buttonStart?.visibility = View.GONE
            } else {
                binding?.textviewCountword?.text = getCountWordTextWithSpannable(it.countWord)
                binding?.buttonStart?.visibility = View.VISIBLE
            }
        }
    }

    private fun initView() {
        binding?.buttonStart?.setOnClickListener(startButtonOnClickListener)

       // binding?.progressTimer?.visibility = View.GONE
     //   binding?.timerText?.visibility = View.GONE

        //binding?.progressTimer?.trackColor = requireContext().getColor(R.color.orange)
    }

    private fun getCountWordTextWithSpannable(count: Long): Spannable {
        val start = getString(R.string.there_are)
        val end = getString(R.string.count_words_end)
        val countToString = count.toString()
        val spannable = SpannableString(
            "$start " +
                    "$count $end"
        )
        val startOrangeText = start.length + 1
        val endOrangeText = startOrangeText + countToString.length

        spannable.setSpan(
            ForegroundColorSpan(requireContext().getColor(R.color.orange)),
            startOrangeText,
            endOrangeText, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return spannable
    }

    private val startButtonOnClickListener = View.OnClickListener {
        binding?.buttonStart?.visibility = View.GONE
        binding?.progressTimer?.visibility = View.VISIBLE
        binding?.timerText?.visibility = View.VISIBLE

        timerAnimation()
    }

    private fun timerAnimation(){
       /* val animator = ValueAnimator.ofObject(ArgbEvaluator(), R.color.orange, R.color.teal_700).apply{
            duration = 1000
            start()
        }*/
        val progressBar = binding?.progressTimer

        val animator = ValueAnimator.ofArgb(requireContext().getColor(R.color.orange),
            requireContext().getColor(R.color.teal_200),
            requireContext().getColor(R.color.purple_200)).apply {
            duration = 2000
            addUpdateListener {
                progressBar?.setIndicatorColor(it.animatedValue as Int)
            }
            start()
        }





       // binding?.progressTimer?.setIndicatorColor(requireContext().getColor(R.color.orange))
    }

    /*
    * ObjectAnimator.ofFloat(textView, "translationX", 100f).apply {
    duration = 1000
    start()
}*/
}
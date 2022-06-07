package com.example.task.wordsfactory.ui.view

import android.animation.AnimatorSet
import android.animation.ValueAnimator
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
        binding?.progressTimer?.visibility = View.GONE
        binding?.timerText?.visibility = View.GONE

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
        val listOfTimer = listOf("GO!", "1", "2", "3", "4", "5")

        val progressBar = binding?.progressTimer
        val textView = binding?.timerText

        val animator = ValueAnimator.ofArgb(requireContext().getColor(R.color.progress5),
            requireContext().getColor(R.color.progress4),
            requireContext().getColor(R.color.progress3),
            requireContext().getColor(R.color.progress2),
            requireContext().getColor(R.color.progress1),
            requireContext().getColor(R.color.progress5)).apply {
            duration = 6000
            addUpdateListener {
                val color = it.animatedValue as Int
                progressBar?.setIndicatorColor(color)
                textView?.setTextColor(color)
            }
        }

        val animatorProgress = ValueAnimator.ofInt(100, 0).apply {
            duration = 6000
            addUpdateListener {
                val progress = it.animatedValue as Int
                progressBar?.progress = it.animatedValue as Int
                if (progress.mod(20) == 0) {
                    val indexForText = progress.div(20)
                    textView?.text = listOfTimer[indexForText]
                }
            }
        }

        val animatorSet = AnimatorSet().apply {
            play(animator).with(animatorProgress)
            start()
        }

        
//        }.apply {
//            println("_________________________________________")
//            Toast.makeText(context, "Тут будет переход дальше!", Toast.LENGTH_LONG)
//        }


    }

}
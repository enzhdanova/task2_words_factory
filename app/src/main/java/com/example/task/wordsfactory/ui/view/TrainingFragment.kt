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
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.core.animation.doOnEnd
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.task.wordsfactory.R
import com.example.task.wordsfactory.databinding.FragmentTrainingBinding
import com.example.task.wordsfactory.ui.viewmodel.TrainingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.max

@AndroidEntryPoint
class TrainingFragment : Fragment() {

    private var binding: FragmentTrainingBinding? = null
    private val viewModel by viewModels<TrainingViewModel>()

    companion object {
        val TAG: String = TrainingFragment::class.java.simpleName
        private const val timerDuration = 5000L
        private const val showWordGoDuration = 1000L
        private const val startProgressValue = 100
        private const val endProgressValue = 0
        private const val timerDurationInSecond = 5
        private val progressPart = max(startProgressValue, endProgressValue).div(
            timerDurationInSecond
        )

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
            val (isVisible, textString) = if (it.countWord == 0L) false to getString(R.string.add_word_in_dictionary)
            else true to getCountWordTextWithSpannable(it.countWord)

            binding?.textviewCountword?.text = textString
            binding?.buttonStart?.isVisible = isVisible
        }
    }

    private fun initView() {
        binding?.buttonStart?.setOnClickListener(startButtonOnClickListener)
        binding?.progressTimer?.isVisible = false
        binding?.timerText?.isVisible = false

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
        binding?.buttonStart?.isVisible = false
        binding?.progressTimer?.isVisible = true
        binding?.timerText?.isVisible = true

        timerAnimation()
    }

    private fun timerAnimation() {
        val progressBar = binding?.progressTimer
        val textView = binding?.timerText

        val animator = ValueAnimator.ofArgb(
            requireContext().getColor(R.color.progress5),
            requireContext().getColor(R.color.progress4),
            requireContext().getColor(R.color.progress3),
            requireContext().getColor(R.color.progress2),
            requireContext().getColor(R.color.progress1)
        ).apply {
            duration = timerDuration
            interpolator = LinearInterpolator()
            addUpdateListener {
                val color = it.animatedValue as Int
                progressBar?.setIndicatorColor(color)
                textView?.setTextColor(color)
            }
        }

        val animatorProgress = ValueAnimator.ofInt(startProgressValue, endProgressValue).apply {
            duration = timerDuration
            interpolator = LinearInterpolator()

            addUpdateListener {
                val progress = it.animatedValue as Int
                progressBar?.progress = it.animatedValue as Int
                if (progress.mod(progressPart) == 0) {
                    textView?.text = progress.div(progressPart).toString()
                }
            }
        }

        val animatorGo = ValueAnimator.ofArgb(
            requireContext().getColor(R.color.progress1),
            requireContext().getColor(R.color.progress5)
        ).apply {
            duration = showWordGoDuration
            interpolator = LinearInterpolator()
            addUpdateListener {
                val color = it.animatedValue as Int
                progressBar?.setIndicatorColor(color)
                textView?.text = getString(R.string.go)
            }
        }

        val animatorSet = AnimatorSet().apply {
            play(animator).with(animatorProgress).before(animatorGo)
            start()
        }

        animatorSet.doOnEnd {
            Toast.makeText(context, getString(R.string.go_next_activity), Toast.LENGTH_LONG).show()
        }
    }
}
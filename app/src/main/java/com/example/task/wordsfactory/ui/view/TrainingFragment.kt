package com.example.task.wordsfactory.ui.view

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import com.example.task.wordsfactory.R
import com.example.task.wordsfactory.TimerColors
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
        binding?.motionlayoutTimer?.addTransitionListener(transactionListener)
        binding?.motionlayoutTimer?.visibility = View.GONE
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

    private val transactionListener = object : MotionLayout.TransitionListener {
        override fun onTransitionStarted(
            motionLayout: MotionLayout?,
            startId: Int,
            endId: Int
        ) {
            var timerColors: TimerColors = TimerColors.PROGRESS5

                when (startId) {
                    R.id.start5 -> timerColors = TimerColors.PROGRESS4
                    R.id.start4 -> timerColors = TimerColors.PROGRESS4
                    R.id.start3 -> timerColors = TimerColors.PROGRESS3
                    R.id.start2 -> timerColors = TimerColors.PROGRESS2
                    R.id.start1 -> timerColors = TimerColors.PROGRESS1
                    R.id.start_go -> timerColors = TimerColors.PROGRESS_GO
                }

                binding?.progressTimer?.progressDrawable =
                    ResourcesCompat.getDrawable(
                        resources,
                        timerColors.progressBarColor,
                        requireContext().theme
                    )
                binding?.timerText?.setTextColor(requireContext().getColor(timerColors.textColor))
        }

        override fun onTransitionChange(
            motionLayout: MotionLayout?,
            startId: Int,
            endId: Int,
            progress: Float
        ) {
        }

        override fun onTransitionCompleted(
            motionLayout: MotionLayout?,
            currentId: Int
        ) {
            when (currentId) {
                R.id.end -> { //TODO: Это значит что таймер истек, переходим дальше
                    Toast.makeText(
                        context,
                        "Дальше будет переход в активити с вопросами",
                        Toast.LENGTH_LONG
                    ).show()
                    binding?.motionlayoutTimer?.visibility = View.GONE
                }
                R.id.start_go -> binding?.progressTimer?.visibility = View.GONE
            }

        }

        override fun onTransitionTrigger(
            motionLayout: MotionLayout?,
            triggerId: Int,
            positive: Boolean,
            progress: Float
        ) {
        }

    }

    private val startButtonOnClickListener = View.OnClickListener {
        binding?.buttonStart?.visibility = View.GONE
        binding?.motionlayoutTimer?.visibility = View.VISIBLE
        binding?.motionlayoutTimer?.transitionToStart()
    }

}
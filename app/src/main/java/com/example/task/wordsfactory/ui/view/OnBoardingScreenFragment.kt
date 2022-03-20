package com.example.task.wordsfactory.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.task.wordsfactory.R
import com.example.task.wordsfactory.data.OnboardingStep
import com.example.task.wordsfactory.databinding.FragmentOnboardingScreensBinding
import com.example.task.wordsfactory.ui.viewmodel.OnBoardingScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

class OnBoardingScreenFragment : Fragment() {

    companion object {
        private const val ARG_OBJECT_TITLE = "title"
        private const val ARG_OBJECT_SUBTITLE = "subtitle"
        private const val ARG_OBJECT_IMAGE = "image"

        fun getFragment(
            onboardingStep: OnboardingStep,
        ): Fragment {
            val fragment = OnBoardingScreenFragment()
            fragment.arguments = bundleOf(
                ARG_OBJECT_TITLE to onboardingStep.title,
                ARG_OBJECT_SUBTITLE to onboardingStep.subtitle,
                ARG_OBJECT_IMAGE to onboardingStep.image
            )
            return fragment
        }
    }

    private var _binding: FragmentOnboardingScreensBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnboardingScreensBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments.let {
            if (it != null) {
                binding?.title?.setText(it.getInt(ARG_OBJECT_TITLE))
                binding?.subtitle?.setText(it.getInt(ARG_OBJECT_SUBTITLE))
                binding?.image?.setImageResource(it.getInt(ARG_OBJECT_IMAGE))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
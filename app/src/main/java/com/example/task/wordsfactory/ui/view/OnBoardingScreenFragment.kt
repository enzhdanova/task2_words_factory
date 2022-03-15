package com.example.task.wordsfactory.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import com.example.task.wordsfactory.R
import com.example.task.wordsfactory.databinding.FragmentOnboardingScreensBinding
import com.example.task.wordsfactory.ui.viewmodel.OnBoardingScreenViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingScreenFragment : Fragment() {

    companion object {
        private const val ARG_OBJECT_POSITION = "position"

        fun getFragment(position: Int): Fragment {
            val fragment = OnBoardingScreenFragment()
            fragment.arguments = bundleOf(
                ARG_OBJECT_POSITION to position
            )
            return fragment
        }
    }

    private val viewModel by viewModels<OnBoardingScreenViewModel>()

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
        var imageViewPositionList: List<ImageView> = listOf(
            binding!!.position1,
            binding!!.position2,
            binding!!.position3
        )

        val position = arguments?.getInt(ARG_OBJECT_POSITION) ?: 0

        viewModel.fetchInfo(position)
        println(viewModel.uiStateLiveData.value)

        viewModel.uiStateLiveData.observe(
            viewLifecycleOwner
        ) { uiState ->
            if (uiState.error) {
                ErrorDialogFragment.getErrorDialog(uiState.errorMessage)
                    .show(this.parentFragmentManager, ErrorDialogFragment.ERROR_TAG)
            } else {
                binding?.title?.setText(uiState.title)
                binding?.subtitle?.setText(uiState.subtitle)
                binding?.image?.setImageResource(uiState.image)
                imageViewPositionList[position].setImageResource(R.drawable.ic_current)
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
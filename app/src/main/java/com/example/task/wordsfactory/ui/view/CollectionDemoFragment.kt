package com.example.task.wordsfactory.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.task.wordsfactory.R
import com.example.task.wordsfactory.data.MockFile
import com.example.task.wordsfactory.ui.viewmodal.OnBoardingScreenViewModel
import dagger.hilt.android.AndroidEntryPoint


class OnBoardingScreenCollectionAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = MockFile.listOfInformation.size

    override fun createFragment(position: Int): Fragment {
        val fragment = OnBoardingScreenFragment()
        fragment.arguments = Bundle().apply {
            putInt(ARG_OBJECT_POSITION, position)
        }
        return fragment
    }


}

private const val ARG_OBJECT_POSITION = "position"

@AndroidEntryPoint
class OnBoardingScreenFragment : Fragment() {
    private val viewModel by viewModels<OnBoardingScreenViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_onboarding_screens, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val textViewTitle: TextView = view.findViewById(R.id.fragment_collection_object_title)
        val textViewSubtitle: TextView = view.findViewById(R.id.fragment_collection_object_subtitle)
        val imageView: ImageView = view.findViewById(R.id.fragment_collection_object_image)
        val imageViewPositionList: List<ImageView> = listOf(
            view.findViewById(R.id.fragment_collection_object_position1),
            view.findViewById(R.id.fragment_collection_object_position2),
            view.findViewById(R.id.fragment_collection_object_position3)
        )
        val position = arguments?.getInt(ARG_OBJECT_POSITION) ?: 0

        viewModel.fetchInfo(position)
        println(viewModel.uiState.value)

        textViewTitle.setText(viewModel.uiState.value.title)
        textViewSubtitle.setText(viewModel.uiState.value.subtitle)
        imageView.setImageResource(viewModel.uiState.value.image)
        imageViewPositionList[position].setImageResource(R.drawable.ic_current)


    }
}
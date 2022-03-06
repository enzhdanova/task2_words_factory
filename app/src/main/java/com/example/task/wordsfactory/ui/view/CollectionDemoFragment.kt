package com.example.task.wordsfactory.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.task.wordsfactory.R
import com.example.task.wordsfactory.data.InformationRepository
import com.example.task.wordsfactory.data.MockFile
import com.example.task.wordsfactory.ui.viewmodal.OnBoardingScreenViewModel
import dagger.hilt.android.AndroidEntryPoint



class DemoCollectionAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = MockFile.listOfInformation.size

    override fun createFragment(position: Int): Fragment {
        val fragment = DemoObjectFragment()
        fragment.arguments = Bundle().apply {
            putInt(ARG_OBJECT_POSITION, position)
        }
        return fragment
    }

}

private const val ARG_OBJECT_POSITION = "position"

@AndroidEntryPoint
class DemoObjectFragment : Fragment() {
    private val viewModel by viewModels<OnBoardingScreenViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_onboarding_screens, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // TODO: кнопки Next и Skip -  часть активити или часть фрагмента?
        val textViewTitle: TextView = view.findViewById(R.id.fragment_collection_object_title)
        val textViewSubtitle: TextView = view.findViewById(R.id.fragment_collection_object_subtitle)
        val imageView: ImageView = view.findViewById(R.id.fragment_collection_object_image)
        val textViewSkip: TextView = view.findViewById(R.id.fragment_collection_object_button_skip)
        val imageViewPositionList: List<ImageView> = listOf(
            view.findViewById(R.id.fragment_collection_object_position1),
            view.findViewById(R.id.fragment_collection_object_position2),
            view.findViewById(R.id.fragment_collection_object_position3)
        )
        val buttonNext: Button = view.findViewById(R.id.fragment_collection_object_button_next)
        var position = arguments?.getInt(ARG_OBJECT_POSITION) ?: 0

        viewModel.fetchInfo(position)

        textViewTitle.text = getString(viewModel.uiState.value.title)
        textViewSubtitle.text = getString(viewModel.uiState.value.subtitle)
        imageView.setImageResource(viewModel.uiState.value.image)
        imageViewPositionList[position].setImageResource(R.drawable.ic_current)
        if (position == 2){
            buttonNext.setText(R.string.start)
        }

    }
}
package com.example.task.wordsfactory.ui.view

import androidx.lifecycle.ViewModel
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
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.task.wordsfactory.R
import com.example.task.wordsfactory.data.InformationRepository
import com.example.task.wordsfactory.data.MockFile
import com.example.task.wordsfactory.ui.viewmodal.OnBoardingScreenViewModel


class DemoCollectionAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    val _viewModel: OnBoardingScreenViewModel = OnBoardingScreenViewModel(InformationRepository())
   // val _viewModel: OnBoardingScreenViewModel by ViewModel()

    override fun getItemCount(): Int = MockFile.listOfInformation.size

    override fun createFragment(position: Int): Fragment {
        val fragment = DemoObjectFragment()
        fragment.arguments = Bundle().apply {
            _viewModel.fetchInfo(position)

            putInt(ARG_OBJECT_TITLE, _viewModel.uiState.value.title)
            putInt(ARG_OBJECT_SUBTITLE, _viewModel.uiState.value.subtitle)
            putInt(ARG_OBJECT_IMAGE, _viewModel.uiState.value.image)
            putInt(ARG_OBJECT_POSITION, position)

//            putInt(ARG_OBJECT_TITLE, information.title)
//            putInt(ARG_OBJECT_SUBTITLE, information.subtitle)
//            putInt(ARG_OBJECT_IMAGE, information.image)
//            putInt(ARG_OBJECT_POSITION, position)
        }
        return fragment
    }

}

private const val ARG_OBJECT_TITLE = "title"
private const val ARG_OBJECT_SUBTITLE = "subtitle"
private const val ARG_OBJECT_IMAGE = "image"
private const val ARG_OBJECT_POSITION = "position"

class DemoObjectFragment : Fragment() {

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
        var position = 0

        arguments?.takeIf {
            it.containsKey(ARG_OBJECT_TITLE)
                    && it.containsKey(ARG_OBJECT_SUBTITLE)
        }?.apply {
            textViewTitle.text = context?.getString(getInt(ARG_OBJECT_TITLE))
            textViewSubtitle.text = context?.getString(getInt(ARG_OBJECT_SUBTITLE))
            imageView.setImageResource(getInt(ARG_OBJECT_IMAGE))

            position = getInt(ARG_OBJECT_POSITION)
        }

        imageViewPositionList[position].setImageResource(R.drawable.ic_current)
        if (position == 2){
            buttonNext.setText(R.string.start)
        }

        textViewSkip.setOnClickListener {
            Toast.makeText(context,"Вы нажали на Skip", Toast.LENGTH_LONG).show()

        }

    }
}
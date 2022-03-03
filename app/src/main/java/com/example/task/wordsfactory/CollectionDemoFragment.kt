package com.example.task.wordsfactory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter


class DemoCollectionAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    //TODO: это лучше убрать в data слой
    data class Information(
        @StringRes val title: Int,
        @StringRes val subtitle: Int,
        @DrawableRes val image: Int
    )
    //TODO: это лучше убрать в data слой
    private val listOfInformation = listOf(
        Information(
            title = R.string.learn,
            subtitle = R.string.subtitle,
            image = R.drawable.ic_cool_kids_long_distance_relationship
        ),
        Information(
            title = R.string.find,
            subtitle = R.string.subtitle,
            image = R.drawable.ic_cool_kids_staying_home
        ),
        Information(
            title = R.string.improve,
            subtitle = R.string.subtitle,
            image = R.drawable.ic_cool_kids_high_tech
        )
    )

    override fun getItemCount(): Int = listOfInformation.size

    override fun createFragment(position: Int): Fragment {
        val fragment = DemoObjectFragment()
        fragment.arguments = Bundle().apply {
            putInt(ARG_OBJECT_TITLE, listOfInformation[position].title)
            putInt(ARG_OBJECT_SUBTITLE, listOfInformation[position].subtitle)
            putInt(ARG_OBJECT_IMAGE, listOfInformation[position].image)
            putInt(ARG_OBJECT_POSITION, position)
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

        val textViewTitle: TextView = view.findViewById(R.id.fragment_collection_object_title)
        val textViewSubtitle: TextView = view.findViewById(R.id.fragment_collection_object_subtitle)
        val imageView: ImageView = view.findViewById(R.id.fragment_collection_object_image)
        val imageViewPositionList: List<ImageView> = listOf(
            view.findViewById(R.id.fragment_collection_object_position1),
            view.findViewById(R.id.fragment_collection_object_position2),
            view.findViewById(R.id.fragment_collection_object_position3)
        )
        val buttonNext: Button = view.findViewById(R.id.fragment_collection_object_button_next)
        var position: Int = 0

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

    }
}
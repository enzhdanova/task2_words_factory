package com.example.task.wordsfactory.ui.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnBoardingScreenCollectionAdapter(
    private val countFragment: Int,
    fragmentActivity: FragmentActivity
) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = countFragment

    override fun createFragment(position: Int): Fragment {
        return OnBoardingScreenFragment.getFragment(position)
    }
}
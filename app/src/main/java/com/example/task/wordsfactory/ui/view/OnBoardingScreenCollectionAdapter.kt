package com.example.task.wordsfactory.ui.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.task.wordsfactory.data.OnboardingInfoEnum
import com.example.task.wordsfactory.ui.viewmodel.OnBoardingScreenUIState
import com.example.task.wordsfactory.ui.viewmodel.OnboardingFragmentUIState

class OnBoardingScreenCollectionAdapter(
    fragmentActivity: FragmentActivity,
    val onboardingFragmentUIStateList: List<OnboardingFragmentUIState>
) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = OnboardingInfoEnum.values().size

    override fun createFragment(position: Int): Fragment {
        return OnBoardingScreenFragment.getFragment(position, onboardingFragmentUIStateList[position])
    }

}
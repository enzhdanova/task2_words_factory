package com.example.task.wordsfactory.ui.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.task.wordsfactory.data.OnboardingStep

class OnBoardingScreenCollectionAdapter(
    fragmentActivity: FragmentActivity,
) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = OnboardingStep.values().size

    override fun createFragment(position: Int): Fragment {
        return OnBoardingScreenFragment.getFragment(onboardingStep = OnboardingStep.values().toList()[position])
    }
}
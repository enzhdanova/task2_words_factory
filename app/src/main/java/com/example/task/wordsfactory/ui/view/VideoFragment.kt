package com.example.task.wordsfactory.ui.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import com.example.task.wordsfactory.BuildConfig
import com.example.task.wordsfactory.databinding.FragmentVideoBinding
import com.example.task.wordsfactory.ui.utils.VideoWebViewClient

class VideoFragment : Fragment() {

    companion object {
        val TAG : String = VideoFragment::class.java.simpleName

        @JvmStatic
        fun newInstance() : Fragment {
            return VideoFragment()
        }
    }

    private var binding: FragmentVideoBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVideoBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.webview?.loadUrl(BuildConfig.VIDEO_URL)
        binding?.webview?.webViewClient = VideoWebViewClient()

        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true){

            override fun handleOnBackPressed() {
                if (binding?.webview?.canGoBack() == true) {
                    binding?.webview?.goBack()
                }
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

}
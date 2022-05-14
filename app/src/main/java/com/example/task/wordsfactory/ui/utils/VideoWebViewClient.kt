package com.example.task.wordsfactory.ui.utils

import android.net.Uri
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.task.wordsfactory.BuildConfig

class VideoWebViewClient : WebViewClient() {

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        if (request?.url?.path?.startsWith(
                Uri.parse(BuildConfig.VIDEO_URL).path ?: "",
                true
            ) == false
        ) {
            view?.stopLoading()
        }
        return false
    }
}
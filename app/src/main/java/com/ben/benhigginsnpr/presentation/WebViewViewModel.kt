package com.ben.benhigginsnpr.presentation

import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WebViewViewModel @Inject constructor() :ViewModel() {

    private val TAG = WebViewViewModel::class.java.simpleName

    private val progressListener = MutableLiveData<Boolean>(false)

    val webViewClient = object:WebViewClient(){

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            progressListener.value = true
        }
    }

    fun getWebViewLoadingListener() = progressListener as LiveData<Boolean>

}
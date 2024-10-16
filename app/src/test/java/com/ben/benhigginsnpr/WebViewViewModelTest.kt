package com.ben.benhigginsnpr

import android.webkit.WebView
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ben.benhigginsnpr.Util.getLiveDataValue
import com.ben.benhigginsnpr.presentation.WebViewViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class WebViewViewModelTest {

    val webVM:WebViewViewModel = WebViewViewModel()

    @get:Rule
    val instantTestRule = InstantTaskExecutorRule()
    val testDispatcher = StandardTestDispatcher()

    val mockWebView = mock(WebView::class.java)

    @Before
    fun setup(){
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun finish(){
        Dispatchers.resetMain()
    }

    @Test
    fun `test webViewClient updates liveData when finished `(){

        webVM.webViewClient.onPageFinished(mockWebView,"fakeurl")

        val value = getLiveDataValue(webVM.getWebViewLoadingListener())

        assert(value == true)
    }

}
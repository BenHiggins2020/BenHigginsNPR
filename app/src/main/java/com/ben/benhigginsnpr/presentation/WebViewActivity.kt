package com.ben.benhigginsnpr.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.viewinterop.AndroidView
import com.ben.benhigginsnpr.MainActivity
import com.ben.benhigginsnpr.ui.LoadingPage
import com.ben.benhigginsnpr.ui.theme.BenHigginsNPRTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebViewActivity: ComponentActivity() {

    private val TAG = WebViewActivity::class.java.simpleName
    private val ARTICLE_URL = "ARTICLE_URL"
    lateinit var webView: WebView

    private val viewModel by viewModels<WebViewViewModel>()
    private lateinit var url:String

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        url = intent.getStringExtra(ARTICLE_URL) ?: "https://www.npr.org/"

        if(url.equals("https://www.npr.org/")){
            Toast.makeText(this,
                "An error occurred when loading the article. Please try again later",
                Toast.LENGTH_LONG).show()
        }

        webView = WebView(this@WebViewActivity).apply {
            loadUrl(this@WebViewActivity.url)
            webViewClient = viewModel.webViewClient
            settings.javaScriptEnabled = true
        }

        setContent {
            Surface {
                BenHigginsNPRTheme {
                    webViewMain()
                }
            }
        }
    }


    @SuppressLint("SetJavaScriptEnabled")
    @Composable
    fun webViewMain(){

        if (viewModel.getWebViewLoadingListener().value == true){
            LoadingPage.LoadingView {
                startActivity(
                    Intent(
                        this@WebViewActivity,
                        MainActivity::class.java
                    )
                )
            }
        } else {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,

            ){

                AndroidView(
                    modifier = Modifier.fillMaxHeight(.95f)
                        .semantics {
                            this.contentDescription = "WebView $url"

                        },
                    factory = {context ->
                       webView
                    })

                Button(
                    modifier = Modifier.semantics { contentDescription = "WebView Back Button" },
                    onClick = {
                        startActivity(Intent(this@WebViewActivity,MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
                        finish()
                    }
                ){
                    Text("Back")
                }

            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        webView.destroy()
    }
}
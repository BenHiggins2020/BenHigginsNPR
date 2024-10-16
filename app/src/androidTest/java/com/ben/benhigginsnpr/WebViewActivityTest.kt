package com.ben.benhigginsnpr

import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.ben.benhigginsnpr.presentation.WebViewActivity
import org.junit.Rule
import org.junit.Test

class WebViewActivityTest {

    @get:Rule
    val rule = createAndroidComposeRule<WebViewActivity>()

    val articleUrl = "https://npr.org/sections/news"

    val errorToastMessage = "An error occurred when loading the article. Please try again later"


    @Test
    fun `test webView displayed`(){
        rule.onNodeWithContentDescription("WebView ",true).isDisplayed()
    }

    @Test
    fun `test toast error message`(){
        rule.onNodeWithText(errorToastMessage).isDisplayed()
    }

    @Test
    fun `test url in webview is correctly set`(){
        rule.activity.intent.putExtra("ARTICLE_URL",articleUrl)
        rule.activityRule.scenario.recreate()

        rule.activityRule.scenario.onActivity {
            assert(it.webView.url == articleUrl)
        }

    }


}
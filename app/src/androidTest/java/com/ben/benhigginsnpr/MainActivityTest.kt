package com.ben.benhigginsnpr

import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val rule = createAndroidComposeRule<MainActivity>()

    val loadingPageText = "Please wait while this page loads"

    @Test
    fun `test loading screen displayed`(){
        val node = rule.onNodeWithText(loadingPageText, ignoreCase = true)
        node.isDisplayed()
    }

    @Test
    fun `test list displayed`(){

        rule.waitUntil(3000){
            val nodes = rule.onAllNodesWithContentDescription("News Image")
            nodes.get(0).isDisplayed()
            nodes.get(1).isDisplayed()
        }

    }

    @Test
    fun `test loading screen cancel button after item press`(){

        rule.waitUntil(3000) { rule.onAllNodesWithContentDescription("News Image").get(0).isDisplayed()}

        rule.onAllNodesWithContentDescription("News Image").get(0).performClick()

        rule.onNodeWithText(loadingPageText).isDisplayed()

        rule.onNode(matcher = hasText("Back")).performClick()

        rule.waitUntil(6000) { rule.onAllNodesWithContentDescription("News Image").get(0).isDisplayed() }

    }

    @Test
    fun `test webview is displayed on click`(){

        rule.waitUntil(3000) { rule.onAllNodesWithContentDescription("News Image").get(0).isDisplayed()}

        rule.onAllNodesWithContentDescription("News Image").get(0).performClick()

        rule.waitUntil(7000){
            rule.onNode(hasContentDescription("WebView",true).and(!hasContentDescription("Button",true))).isDisplayed()
        }


    }

    //Testing in the MainActivityTest allows us to confirm that after back button is pressed,
    //the WebViewActivity is finished, returning to the MainActivity page presenting the list again
    @Test
    fun `test back button on webview`(){

        rule.waitUntil(3000) { rule.onAllNodesWithContentDescription("News Image").get(0).isDisplayed()}

        rule.onAllNodesWithContentDescription("News Image").get(0).performClick()

        rule.waitUntil(4000) {
            rule.onNode(hasContentDescription("WebView",true).and(!hasContentDescription("Button",true))).isDisplayed()
        }
        rule.onNodeWithContentDescription("WebView Back Button").performClick()

        rule.onNodeWithText(loadingPageText).isDisplayed()

        rule.waitUntil(3000) { rule.onAllNodesWithContentDescription("News Image").get(0).isDisplayed()}

    }

}
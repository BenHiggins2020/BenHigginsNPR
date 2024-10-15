package com.ben.benhigginsnpr.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

class LoadingPage {
    companion object {
        @Composable
        fun LoadingView(backButton: ()->Unit){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(
                    text ="Please wait while this page loads...",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )

                CircularProgressIndicator(
                    modifier = Modifier.fillMaxSize(.35f)
                )

                Button(
                    onClick = backButton
                ){
                    Text("Cancel")
                }

            }

        }

    }

}
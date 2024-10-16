package com.ben.benhigginsnpr

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.ben.benhigginsnpr.domain.HeadLineItem
import com.ben.benhigginsnpr.presentation.MainViewModel
import com.ben.benhigginsnpr.presentation.WebViewActivity
import com.ben.benhigginsnpr.ui.LoadingPage
import com.ben.benhigginsnpr.ui.theme.BenHigginsNPRTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel:MainViewModel by viewModels<MainViewModel>()
    private val TAG = MainActivity::class.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch(Dispatchers.IO) {
            repeatOnLifecycle(Lifecycle.State.CREATED){
                Log.d(TAG,"Fetching NPR Headlines")
                val job = viewModel.fetchHeadlines()

            }
        }

        setContent {
            BenHigginsNPRTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    mainView()
                }
            }
        }
    }

    @Composable
    fun mainView() {

        val headLines = remember { mutableStateOf(listOf<HeadLineItem>()) }

        viewModel.getLiveDataList().observe(this@MainActivity){ it->
            headLines.value = it
        }

        if(headLines.value.isNotEmpty()){
            List(headLines.value)
        } else {
            LoadingPage.LoadingView {
                //Do nothing
            }
        }

    }

    @Composable
    fun List(newsItems:List<HeadLineItem>){
        LazyColumn {
            items(newsItems.size) { index ->
               headlinesItem(newsItems.get(index))
            }
        }
    }

    @Composable
    fun headlinesItem(item:HeadLineItem){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
                .clickable {
                    Log.d(TAG, "Clicked: ${item.articleUrl}")
                    val intent = Intent(this, WebViewActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .putExtra("ARTICLE_URL", item.articleUrl)

                    startActivity(intent)
                }
        ) {
            ImageItem(item.imageUrl)
            Text(text = item.title)
        }
    }


    @Composable
    fun ImageItem(imageUrl:String){

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .build(),
                contentDescription = "News Image",
                imageLoader = ImageLoader(this@MainActivity),
                placeholder = painterResource(R.drawable.ic_launcher_background),
                modifier = Modifier
                    .padding(16.dp)
                    .height(100.dp)
                    .width(100.dp)
                    .border(1.dp, MaterialTheme.colorScheme.primary, CircleShape)
                    .clip(CircleShape)
            )
    }

}




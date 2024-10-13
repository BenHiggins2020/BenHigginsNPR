package com.ben.benhigginsnpr

import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Spinner
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.colorSpace
import coil3.request.lifecycle
import com.ben.benhigginsnpr.data.headline.data.AttributesX
import com.ben.benhigginsnpr.data.headline.data.Item
import com.ben.benhigginsnpr.data.headline.data.NPRHeadlineItem
import com.ben.benhigginsnpr.presentation.MainViewModel
import com.ben.benhigginsnpr.ui.theme.BenHigginsNPRTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

//@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel:MainViewModel by viewModels<MainViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launchWhenCreated {
            println("BEN - CALLING GETNPRHEADLINES")
            viewModel.getNPRHeadlines()
        }

        setContent {
            BenHigginsNPRTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        var text = remember { mutableStateOf(name) }

        var headLines = remember { mutableStateOf(listOf<Item>()) }

        viewModel.getLiveDataList().observe(this@MainActivity){ it->
            text.value = it.items.get(0).attributes.title
            headLines.value = it.items
        }

        println("BEN - Loading the text value ")

        if(headLines.value.isNotEmpty()){
            List(headLines.value)
        } else {
            Spinner(this@MainActivity)
        }

    }


    @Composable
    fun List(newsItems:List<Item>){
        LazyColumn {
            items(newsItems.size) { index ->
               headlinesItem(newsItems.get(index))
            }
        }
    }

    @Composable
    fun headlinesItem(item:Item){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
                .clickable {
                    println("BEN - Clicked: Navigate to this WebviewPage ${item.links.web.get(0).href}")
                    //start activity with webview for

                }
        ) {
            ImageItem(item.links.image[2].href)
            Text(text = item.attributes.title)
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
                    .border(1.dp, MaterialTheme.colorScheme.primary,CircleShape)
                    .clip(CircleShape)
            )
    }

}




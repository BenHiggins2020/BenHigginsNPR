package com.ben.benhigginsnpr.domain

import android.util.Log
import com.ben.benhigginsnpr.data.NPRHeadlinesApi
import javax.inject.Inject

 class HeadlineDataManager @Inject constructor(
     private val nprHeadlinesApi: NPRHeadlinesApi
 ) {

    private val TAG = HeadlineDataManager::class.simpleName

    suspend fun getHeadlinesList(): List<HeadLineItem>? {
        try {
            val headLines = mutableListOf<HeadLineItem>()
            nprHeadlinesApi.getHeadlines().body().let { it ->
                it?.items?.map {
                   val item =  HeadLineItem(
                        title = it.attributes.title,
                        imageUrl = it.links.image[2].href,
                        articleUrl = it.links.web[0].href
                   )
                    headLines.add(item)
                }
                return headLines as List<HeadLineItem>
            }
        } catch (e:Exception){
            Log.e(TAG,"Api call failed with exception: $e")
            //TODO: Handle retry or throw exception
            return emptyList()
        }
    }
}
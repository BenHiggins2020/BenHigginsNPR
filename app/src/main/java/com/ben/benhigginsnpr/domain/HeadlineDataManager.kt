package com.ben.benhigginsnpr.domain

import android.util.Log
import com.ben.benhigginsnpr.data.NPRHeadlinesApi
import com.ben.benhigginsnpr.data.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HeadlineDataManager @Inject constructor() {

    private val TAG = HeadlineDataManager::class.simpleName

   suspend fun getHeadlines() = flow {

            try {
                val nprData = RetrofitInstance.nprService.getHeadlines() ?: null
                if(nprData?.body() == null) return@flow
                nprData!!.body().let {
                    it!!.items.map {
                        emit(
                            HeadLineItem(
                            title = it.attributes.title,
                            imageUrl = it.links.image[2].href,
                            videoUrl = it.links.web[0].href
                             )
                        )
                    }

                }

            } catch (e:Exception){
                Log.e("HeadlineDataManager","Error getting headlines $e")
            }
   }

    suspend fun getHeadlinesList(): List<HeadLineItem>? {
        try {
            val headLines = mutableListOf<HeadLineItem>()
            RetrofitInstance.nprService.getHeadlines().body().let { it ->
                it?.items?.map {
                   val item =  HeadLineItem(
                        title = it.attributes.title,
                        imageUrl = it.links.image[2].href,
                        videoUrl = it.links.web[0].href
                   )
                    headLines.add(item)
                }
                return headLines as List<HeadLineItem>
            }
        }catch (e:Exception){
            Log.e(TAG,"Api call failed with exception: $e")
            return null
        }
    }
}
package com.ben.benhigginsnpr.domain

import android.util.Log
import com.ben.benhigginsnpr.data.NPRHeadlinesApi
import com.ben.benhigginsnpr.data.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HeadlineDataManager @Inject constructor() {

   suspend fun getHeadlines() = flow {

            println("BEN - getHeadlines API calling...")
            try {
                val nprData = RetrofitInstance.nprService.getHeadlines()
                val raw = nprData.raw()
                val data = nprData.body()

                println("BEN - getHeadlines API returned ${raw.isSuccessful} ${raw}  ${data?.items?.get(0)} ${data?.items?.size}")
                emit(data)

            } catch (e:Exception){
                Log.e("HeadlineDataManager","Error getting headlines $e")
            }


    }

}
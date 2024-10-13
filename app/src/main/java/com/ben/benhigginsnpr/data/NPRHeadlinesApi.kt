package com.ben.benhigginsnpr.data

import com.ben.benhigginsnpr.data.headline.data.NPRHeadlineItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NPRHeadlinesApi {

    @GET("assets/api/listening-api-response.json")
    suspend fun getHeadlines(): Response<NPRHeadlineItem>

}
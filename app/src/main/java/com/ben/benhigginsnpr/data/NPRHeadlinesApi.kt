package com.ben.benhigginsnpr.data

import com.ben.benhigginsnpr.data.headline.classes.NPRHeadlineItem
import retrofit2.Response
import retrofit2.http.GET

interface NPRHeadlinesApi {

    @GET("assets/api/listening-api-response.json")
    suspend fun getHeadlines(): Response<NPRHeadlineItem>

}
package com.ben.benhigginsnpr.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val nprService:NPRHeadlinesApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://legacy.npr.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NPRHeadlinesApi::class.java)
    }
}
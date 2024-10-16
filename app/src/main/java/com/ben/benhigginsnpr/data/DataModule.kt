package com.ben.benhigginsnpr.data

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun bindNPRApiService():NPRHeadlinesApi {
        return Retrofit.Builder()
            .baseUrl("https://legacy.npr.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NPRHeadlinesApi::class.java)
    }
}
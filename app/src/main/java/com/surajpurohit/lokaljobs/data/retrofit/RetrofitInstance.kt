package com.surajpurohit.lokaljobs.data.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://testapi.getlokalapp.com/common/" // Replace with your actual base URL

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: JobApiService by lazy {
        retrofit.create(JobApiService::class.java)
    }
}
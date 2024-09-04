package com.surajpurohit.lokaljobs.data.retrofit

import com.surajpurohit.lokaljobs.data.model.JobResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface JobApiService {
    @GET("jobs")
    suspend fun getJobs(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): Response<JobResult>
}
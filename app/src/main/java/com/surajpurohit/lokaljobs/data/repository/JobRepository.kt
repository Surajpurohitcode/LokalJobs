package com.surajpurohit.lokaljobs.data.repository

import com.surajpurohit.lokaljobs.data.model.JobResult
import com.surajpurohit.lokaljobs.data.retrofit.JobApiService
import com.surajpurohit.lokaljobs.utils.Resource
import retrofit2.HttpException
import java.io.IOException

class JobRepository(val apiService: JobApiService) {

    suspend fun getJobs(page: Int, pageSize: Int): Resource<JobResult> {
        return try {
            val response = apiService.getJobs(page, pageSize)
            if (response.isSuccessful) {
                response.body()?.let {
                    Resource.Success(it)
                } ?: Resource.Error("No data available")
            } else {
                Resource.Error("Error: ${response.code()}")
            }
        } catch (exception: IOException) {
            Resource.Error("Network error: ${exception.message}")
        } catch (exception: HttpException) {
            Resource.Error("HTTP error: ${exception.message()}")
        }
    }
}

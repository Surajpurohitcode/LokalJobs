package com.surajpurohit.lokaljobs.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.surajpurohit.lokaljobs.data.model.JobDetails
import com.surajpurohit.lokaljobs.data.model.JobResult
import com.surajpurohit.lokaljobs.data.retrofit.JobApiService
import retrofit2.HttpException
import java.io.IOException

class JobPagingSource(private val apiService: JobApiService) : PagingSource<Int, JobDetails>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, JobDetails> {
        val page = params.key ?: 1
        return try {
            val response = apiService.getJobs(page, params.loadSize)
            val jobs = response.body()?.results ?: emptyList() // Provide an empty list if null

            LoadResult.Page(
                data = jobs,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (jobs.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, JobDetails>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val page = state.closestPageToPosition(anchorPosition)
            page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
        }
    }
}


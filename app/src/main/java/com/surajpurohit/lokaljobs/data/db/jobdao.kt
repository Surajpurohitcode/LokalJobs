package com.surajpurohit.lokaljobs.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.surajpurohit.lokaljobs.data.model.BookmarkedJob

@Dao
interface JobDao {
    @Insert
    suspend fun insertJob(job: BookmarkedJob)

    @Query("SELECT * FROM bookmarked_jobs WHERE id = :jobId")
    suspend fun getJobById(jobId: String): BookmarkedJob?

    @Query("DELETE FROM bookmarked_jobs WHERE id = :jobId")
    suspend fun deleteJobById(jobId: String)

    @Query("SELECT * FROM bookmarked_jobs")
    suspend fun getAllBookmarkedJobs(): List<BookmarkedJob>
}
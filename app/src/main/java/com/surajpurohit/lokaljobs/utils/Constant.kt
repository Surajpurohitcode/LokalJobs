package com.surajpurohit.lokaljobs.utils

import android.content.Context
import com.surajpurohit.lokaljobs.data.db.AppDatabase
import com.surajpurohit.lokaljobs.data.model.BookmarkedJob
import com.surajpurohit.lokaljobs.data.model.JobDetails
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object Constant {
    fun bookmarkJob(context: Context, job: JobDetails) {
        val database = AppDatabase.getDatabase(context)
        val jobDao = database.jobDao()

        val bookmarkedJob = BookmarkedJob(
            id = job.id.toString(), // Ensure job has a unique identifier
            companyName = job.company_name,
            jobTitle = job.title,
            jobLocation = job.primary_details.place,
            jobMinSalary = job.salary_min?.toString(),
            jobMaxSalary = job.salary_max?.toString()
        )

        CoroutineScope(Dispatchers.IO).launch {
            jobDao.insertJob(bookmarkedJob)
        }
    }
}
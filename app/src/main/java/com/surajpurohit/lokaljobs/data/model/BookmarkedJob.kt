package com.surajpurohit.lokaljobs.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarked_jobs")
data class BookmarkedJob(
    @PrimaryKey val id: String,
    val companyName: String,
    val jobTitle: String,
    val jobLocation: String,
    val jobMinSalary: String?,
    val jobMaxSalary: String?
)
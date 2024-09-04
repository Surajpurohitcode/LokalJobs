package com.surajpurohit.lokaljobs.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.surajpurohit.lokaljobs.data.repository.JobRepository

class JobPagingViewModelFactory(private val jobRepository: JobRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JobPagingViewModel::class.java)) {
            return JobPagingViewModel(jobRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

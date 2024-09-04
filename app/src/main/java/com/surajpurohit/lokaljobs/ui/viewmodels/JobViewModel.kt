package com.surajpurohit.lokaljobs.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import androidx.paging.liveData
import com.surajpurohit.lokaljobs.data.model.JobDetails
import com.surajpurohit.lokaljobs.data.repository.JobRepository
import com.surajpurohit.lokaljobs.paging.JobPagingSource
import com.surajpurohit.lokaljobs.utils.Resource
import kotlinx.coroutines.launch

class JobPagingViewModel(private val jobRepository: JobRepository) : ViewModel() {

    val jobs: LiveData<PagingData<JobDetails>> = Pager(
        PagingConfig(pageSize = 10, enablePlaceholders = false)
    ) {
        JobPagingSource(jobRepository.apiService)
    }.liveData.cachedIn(viewModelScope)

    private val _state = MutableLiveData<Resource<Unit>>()
    val state: LiveData<Resource<Unit>> get() = _state

    private var currentPagingData: PagingData<JobDetails> = PagingData.empty()

    init {
        fetchInitialData()
    }

    private fun fetchInitialData() {
        viewModelScope.launch {
            _state.value = Resource.Loading()
            try {
                val result = jobRepository.getJobs(1, 10)
                _state.value = when (result) {
                    is Resource.Success -> Resource.Success(Unit)
                    is Resource.Error -> Resource.Error(result.message ?: "Unknown error")
                    is Resource.Loading -> Resource.Loading()
                }
            } catch (e: Exception) {
                _state.value = Resource.Error(e.message ?: "Unknown error")
            }
        }
    }
}





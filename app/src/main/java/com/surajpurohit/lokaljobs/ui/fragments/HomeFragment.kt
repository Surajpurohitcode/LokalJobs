package com.surajpurohit.lokaljobs.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.surajpurohit.lokaljobs.R
import com.surajpurohit.lokaljobs.data.adapter.JobLoadStateAdapter
import com.surajpurohit.lokaljobs.data.adapter.JobPagingAdapter
import com.surajpurohit.lokaljobs.data.repository.JobRepository
import com.surajpurohit.lokaljobs.data.retrofit.JobApiService
import com.surajpurohit.lokaljobs.data.retrofit.RetrofitInstance
import com.surajpurohit.lokaljobs.databinding.FragmentHomeBinding
import com.surajpurohit.lokaljobs.ui.viewmodels.JobPagingViewModel
import com.surajpurohit.lokaljobs.ui.viewmodels.JobPagingViewModelFactory
import com.surajpurohit.lokaljobs.utils.Resource
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var viewModel: JobPagingViewModel
    private lateinit var adapter: JobPagingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        bindView(binding)

        return binding.root
    }

    private fun bindView(binding: FragmentHomeBinding) {

        adapter = JobPagingAdapter(requireContext())

        binding.apply {
            jobsRecyclerView.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            jobsRecyclerView.adapter = adapter

            val jobRepository = JobRepository(RetrofitInstance.api)
            val viewModelFactory = JobPagingViewModelFactory(jobRepository)
            viewModel = ViewModelProvider(
                requireActivity(),
                viewModelFactory
            ).get(JobPagingViewModel::class.java)

            viewModel.jobs.observe(viewLifecycleOwner) { pagingData ->
                lifecycleScope.launch {
                    if (pagingData != null) {
                        adapter.submitData(pagingData)
                    }
                }
            }

            jobsRecyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
                header = JobLoadStateAdapter(),
                footer = JobLoadStateAdapter()
            )



            viewModel.state.observe(viewLifecycleOwner) { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        progressBar.visibility = View.VISIBLE
                        jobsRecyclerView.visibility = View.GONE
                    }

                    is Resource.Success -> {
                        progressBar.visibility = View.GONE
                        jobsRecyclerView.visibility = View.VISIBLE
                    }

                    is Resource.Error -> {
                        progressBar.visibility = View.GONE
                        errorState.visibility = View.VISIBLE
                        // Show error message, maybe with a Toast or TextView
                    }
                }
            }

        }

    }

}
package com.surajpurohit.lokaljobs.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.surajpurohit.lokaljobs.R
import com.surajpurohit.lokaljobs.data.adapter.BookmarkedJobsAdapter
import com.surajpurohit.lokaljobs.data.db.AppDatabase
import com.surajpurohit.lokaljobs.databinding.FragmentBookmarksBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BookmarksFragment : Fragment() {

    private lateinit var binding: FragmentBookmarksBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookmarksBinding.inflate(layoutInflater,container,false)


        binding.bookmarksList.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)

        fetchBookmarkedJobs()

        return binding.root
    }

    private fun fetchBookmarkedJobs() {
        val database = AppDatabase.getDatabase(requireContext())
        CoroutineScope(Dispatchers.IO).launch {
            val jobs = database.jobDao().getAllBookmarkedJobs() // Assuming getAllBookmarkedJobs() is defined
            withContext(Dispatchers.Main) {
                binding.bookmarksList.adapter = BookmarkedJobsAdapter(requireContext(),jobs)
            }
        }
    }
}
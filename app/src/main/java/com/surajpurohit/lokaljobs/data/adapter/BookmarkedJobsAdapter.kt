package com.surajpurohit.lokaljobs.data.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.surajpurohit.lokaljobs.R
import com.surajpurohit.lokaljobs.data.model.BookmarkedJob
import com.surajpurohit.lokaljobs.databinding.JobCardViewBinding
import com.surajpurohit.lokaljobs.ui.activities.JobActivity

class BookmarkedJobsAdapter(private val context: Context, private val jobs: List<BookmarkedJob>) :
    RecyclerView.Adapter<BookmarkedJobsAdapter.BookmarkedJobViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkedJobViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.job_card_view, parent, false)
        return BookmarkedJobViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookmarkedJobViewHolder, position: Int) {
        val job = jobs[position]
        holder.binding.companyName.text = job.companyName
        holder.binding.jobTitle.text = job.jobTitle
        holder.binding.jobLocation.text = job.jobLocation
        holder.binding.jobSalary.text = "${job.jobMinSalary} - ${job.jobMaxSalary}/monthly"

    }

    override fun getItemCount(): Int = jobs.size

    class BookmarkedJobViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = JobCardViewBinding.bind(itemView)
    }
}

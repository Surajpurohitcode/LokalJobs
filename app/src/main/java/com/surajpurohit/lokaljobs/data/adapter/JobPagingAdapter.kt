package com.surajpurohit.lokaljobs.data.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.surajpurohit.lokaljobs.R
import com.surajpurohit.lokaljobs.data.model.JobDetails
import com.surajpurohit.lokaljobs.databinding.JobCardViewBinding
import com.surajpurohit.lokaljobs.ui.activities.JobActivity

class JobPagingAdapter(val context: Context) :
    PagingDataAdapter<JobDetails, JobPagingAdapter.JobViewHolder>(DIFF_CALLBACK) {

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        val job = getItem(position)
        if (job != null) {
            try {
                holder.binding.apply {
                    companyName.text = job.company_name
                    jobTitle.text = job.title
                    jobSalary.text = "${job.salary_min} - ${job.salary_max}"
                    jobLocation.text = job.primary_details.place
                    callHrButton.text = job.button_text

                    // Ensure creatives list is not empty and position is valid
                    if (job.creatives.isNotEmpty() && position < job.creatives.size) {
                        Glide.with(context)
                            .load(job.creatives[position].file)
                            .into(comapnyLogo)
                    } else {
                        // Handle empty or invalid creatives list
                        Glide.with(context)
                            .load(R.drawable.companylogo) // Placeholder image
                            .into(comapnyLogo)
                    }

                    callHrButton.setOnClickListener {
                        try {
                            val intent = Intent(Intent.ACTION_DIAL).apply {
                                data = Uri.parse("${job.custom_link}")
                            }
                            context.startActivity(intent)
                        } catch (e: Exception) {
                            Log.e("JobPagingAdapter", "Error starting dialer intent", e)
                            Toast.makeText(context, "Error dialing", Toast.LENGTH_SHORT).show()
                        }
                    }

                    viewMoreButton.setOnClickListener {
                        try {
                            val intent = Intent(holder.itemView.context, JobActivity::class.java).apply {
                                putExtra("jobID", job.id)
                                putExtra("companyName", job.company_name)
                                putExtra("jobTitle", job.title)
                                putExtra("jobLocation", job.primary_details.place)
                                putExtra("jobMinSalary", job.salary_min?.toString() ?: "Not specified")
                                putExtra("jobMaxSalary", job.salary_max?.toString() ?: "Not specified")
                                putExtra("jobDescription", job.other_details?.toString() ?: "Not specified")
                                putExtra("phoneNumber", job.custom_link?.toString() ?: "Not specified")
                            }
                            context.startActivity(intent)
                        } catch (e: Exception) {
                            Log.e("JobPagingAdapter", "Error starting JobActivity", e)
                            Toast.makeText(context, "Error opening job details", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("JobPagingAdapter", "Binding error at position $position", e)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.job_card_view, parent, false)
        return JobViewHolder(view)
    }

    class JobViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = JobCardViewBinding.bind(itemView)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<JobDetails>() {
            override fun areItemsTheSame(oldItem: JobDetails, newItem: JobDetails): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: JobDetails, newItem: JobDetails): Boolean {
                return oldItem == newItem
            }
        }
    }
}

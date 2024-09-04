package com.surajpurohit.lokaljobs.ui.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.surajpurohit.lokaljobs.R
import com.surajpurohit.lokaljobs.data.db.AppDatabase
import com.surajpurohit.lokaljobs.data.model.BookmarkedJob
import com.surajpurohit.lokaljobs.databinding.ActivityJobBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JobActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityJobBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        bindView(binding)
    }

    private fun bindView(binding: ActivityJobBinding) {

        binding.toolbar.setNavigationOnClickListener {
            finish()
        }


        val jobID = intent.getStringExtra("jobID")
        val companyName = intent.getStringExtra("companyName")
        val jobTitle = intent.getStringExtra("jobTitle")
        val jobLocation = intent.getStringExtra("jobLocation")
        val jobMinSalary = intent.getStringExtra("jobMinSalary") ?: "Not specified"
        val jobMaxSalary = intent.getStringExtra("jobMaxSalary") ?: "Not specified"
        val jobDescription = intent.getStringExtra("jobDescription") ?: "Not specified"
        val phoneNumber = intent.getStringExtra("phoneNumber") ?: "Not specified"

        binding.companyName.text = companyName
        binding.jobTitle.text = jobTitle
        binding.jobLocation.text = jobLocation
        binding.jobSalary.text = "${jobMinSalary} - ${jobMaxSalary}/monthly"
        binding.jobDescription.text = jobDescription



        binding.callButton.setOnClickListener {
            try {
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse(phoneNumber)
                }
                startActivity(intent)
            } catch (e: Exception) {
                Log.e("JobPagingAdapter", "Error starting dialer intent", e)
                Toast.makeText(this, "Error dialing", Toast.LENGTH_SHORT).show()
            }
        }

        binding.addBookmark.setOnClickListener {
            val job = BookmarkedJob(
                id = jobID?:"Unkown",
                companyName = companyName?:"Unkown",
                jobTitle = jobTitle?:"Unkown",
                jobLocation = jobLocation?:"Unkown",
                jobMinSalary = jobMinSalary,
                jobMaxSalary = jobMaxSalary,
            )
            bookmarkJob(job)
        }


    }

    private fun bookmarkJob(job: BookmarkedJob) {
        val database = AppDatabase.getDatabase(this)
        CoroutineScope(Dispatchers.IO).launch {
            database.jobDao().insertJob(job)
            runOnUiThread {
                Toast.makeText(this@JobActivity, "Job Bookmarked!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
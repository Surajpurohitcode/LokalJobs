package com.surajpurohit.lokaljobs.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.surajpurohit.lokaljobs.R
import com.surajpurohit.lokaljobs.databinding.ItemLoadStateBinding

class JobLoadStateAdapter() : LoadStateAdapter<JobLoadStateAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemLoadStateBinding.bind(itemView)

        fun bind(loadState: LoadState) {
            binding.progressBar.isVisible = loadState is LoadState.Loading
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): JobLoadStateAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_load_state,parent,false)
        return ViewHolder(view)
    }
}

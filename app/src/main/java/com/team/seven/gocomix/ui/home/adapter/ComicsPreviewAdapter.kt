package com.team.seven.gocomix.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.team.seven.gocomix.R
import com.team.seven.gocomix.databinding.ItemComixPreviewBinding
import com.team.seven.gocomix.model.Comix
import com.team.seven.gocomix.ui.home.adapter.ComicsPreviewAdapter.ComicViewHolder

class ComicsPreviewAdapter : ListAdapter<Comix, ComicViewHolder>(
    ComicDiffCallback()
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ComicViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemComixPreviewBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.item_comix_preview,
            parent,
            false
        )
        return ComicViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ComicViewHolder,
        position: Int
    ) {
        holder.bindTo(getItem(position))
    }

    class ComicViewHolder internal constructor(
        private val binding: ItemComixPreviewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindTo(comic: Comix) {
            binding.comix = comic
            binding.infoClickListener = {}
            binding.executePendingBindings()
        }
    }

    private class ComicDiffCallback : DiffUtil.ItemCallback<Comix>() {

        override fun areItemsTheSame(
            oldItem: Comix,
            newItem: Comix
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Comix,
            newItem: Comix
        ): Boolean {
            return oldItem == newItem
        }
    }
}

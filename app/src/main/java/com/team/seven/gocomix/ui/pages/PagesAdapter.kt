package com.team.seven.gocomix.ui.pages

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.team.seven.gocomix.R
import com.team.seven.gocomix.data.entity.Page
import com.team.seven.gocomix.databinding.ItemPageBinding
import com.team.seven.gocomix.ui.pages.PagesAdapter.PageViewHolder

class PagesAdapter : ListAdapter<Page, PageViewHolder>(
    PagesDiffCallback()
) {

    var loadedListener: ((image: Bitmap) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemPageBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.item_page,
            parent,
            false
        )
        return PageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    inner class PageViewHolder internal constructor(
        private val binding: ItemPageBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindTo(page: Page) {
            binding.page = page
            binding.loadedListener = loadedListener
            binding.executePendingBindings()
        }
    }

    private class PagesDiffCallback : DiffUtil.ItemCallback<Page>() {

        override fun areItemsTheSame(
            oldItem: Page,
            newItem: Page
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Page,
            newItem: Page
        ): Boolean {
            return oldItem == newItem
        }
    }
}

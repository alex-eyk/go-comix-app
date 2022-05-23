package com.team.seven.gocomix.ui.comics

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.team.seven.gocomix.data.entity.Comic
import com.team.seven.gocomix.ui.comics.AbstractComicsAdapter.AbstractComicViewHolder

abstract class AbstractComicsAdapter<B : ViewDataBinding, VH: AbstractComicViewHolder<B>>(
    @LayoutRes private val layoutRes: Int
) : ListAdapter<Comic, VH>(ComicDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        val binding: B = DataBindingUtil
            .inflate(inflater, layoutRes, parent, false)
        return onCreateViewHolder(binding)
    }

    abstract fun onCreateViewHolder(binding: B): VH

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bindTo(getItem(position))
    }

    abstract class AbstractComicViewHolder<B : ViewDataBinding>(
        protected val binding: B
    ) : RecyclerView.ViewHolder(binding.root) {

        abstract fun bindTo(comic: Comic)
    }

    private class ComicDiffCallback : DiffUtil.ItemCallback<Comic>() {

        override fun areItemsTheSame(old: Comic, new: Comic) = old.id == new.id

        override fun areContentsTheSame(old: Comic, new: Comic) = old == new
    }
}

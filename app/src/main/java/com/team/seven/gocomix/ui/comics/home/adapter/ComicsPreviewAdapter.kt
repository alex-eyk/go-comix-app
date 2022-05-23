package com.team.seven.gocomix.ui.comics.home.adapter

import com.team.seven.gocomix.R
import com.team.seven.gocomix.data.entity.Comic
import com.team.seven.gocomix.databinding.ItemComixPreviewBinding
import com.team.seven.gocomix.ui.comics.AbstractComicsAdapter
import com.team.seven.gocomix.ui.comics.home.adapter.ComicsPreviewAdapter.ComicsViewHolder

class ComicsPreviewAdapter : AbstractComicsAdapter<ItemComixPreviewBinding, ComicsViewHolder>(
    R.layout.item_comix_preview
) {

    var infoListener: ((comic: Comic) -> Unit)? = null
    var readListener: ((comic: Comic) -> Unit)? = null

    override fun onCreateViewHolder(binding: ItemComixPreviewBinding): ComicsViewHolder {
        return ComicsViewHolder(binding)
    }

    inner class ComicsViewHolder(
        binding: ItemComixPreviewBinding
    ) : AbstractComicViewHolder<ItemComixPreviewBinding>(binding) {

        override fun bindTo(comic: Comic) {
            binding.comic = comic
            binding.infoListener = infoListener ?: {}
            binding.readListener = readListener ?: {}
            binding.executePendingBindings()
        }
    }
}

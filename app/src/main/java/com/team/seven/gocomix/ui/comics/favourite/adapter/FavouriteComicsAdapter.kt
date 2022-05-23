package com.team.seven.gocomix.ui.comics.favourite.adapter

import com.team.seven.gocomix.R
import com.team.seven.gocomix.data.entity.Comic
import com.team.seven.gocomix.databinding.ItemFavouriteComixBinding
import com.team.seven.gocomix.ui.comics.AbstractComicsAdapter
import com.team.seven.gocomix.ui.comics.favourite.adapter.FavouriteComicsAdapter.FavouriteComicViewHolder

class FavouriteComicsAdapter :
    AbstractComicsAdapter<ItemFavouriteComixBinding, FavouriteComicViewHolder>(
        layoutRes = R.layout.item_favourite_comix
    ) {

    override fun onCreateViewHolder(
        binding: ItemFavouriteComixBinding
    ) = FavouriteComicViewHolder(binding)

    class FavouriteComicViewHolder(
        binding: ItemFavouriteComixBinding
    ) : AbstractComicViewHolder<ItemFavouriteComixBinding>(binding) {

        override fun bindTo(comic: Comic) {
            binding.comic = comic
            binding.executePendingBindings()
        }
    }
}

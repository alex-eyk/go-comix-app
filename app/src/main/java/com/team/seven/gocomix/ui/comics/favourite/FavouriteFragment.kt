package com.team.seven.gocomix.ui.comics.favourite

import androidx.fragment.app.viewModels
import com.team.seven.gocomix.R
import com.team.seven.gocomix.databinding.FragmentFavouriteBinding
import com.team.seven.gocomix.ui.AbstractFragment
import com.team.seven.gocomix.ui.UiState
import com.team.seven.gocomix.ui.comics.favourite.adapter.FavouriteComicsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteFragment : AbstractFragment<FragmentFavouriteBinding, FavouriteViewModel>(
    layoutRes = R.layout.fragment_favourite
) {

    override val viewModel: FavouriteViewModel by viewModels()

    private val favouriteComicsAdapter = FavouriteComicsAdapter()

    override fun onBindingCreated() {
        super.onBindingCreated()
        binding.apply {
            favouriteComicsRecyclerView.apply {
                adapter = favouriteComicsAdapter
            }
        }
    }

    override suspend fun onCollectStates() {
        super.onCollectStates()
        viewModel.favouriteComicsState.collect {
            when (it) {
                is UiState.Success -> {
                    favouriteComicsAdapter.submitList(it.value)
                }
                is UiState.Failure -> {
                }
            }
        }
    }
}

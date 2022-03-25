package com.team.seven.gocomix.ui.favourite

import androidx.fragment.app.viewModels
import com.team.seven.gocomix.R
import com.team.seven.gocomix.databinding.FragmentFavouriteBinding
import com.team.seven.gocomix.ui.AbstractFragment

class FavouriteFragment : AbstractFragment<FragmentFavouriteBinding>(
    R.layout.fragment_favourite
) {

    private val viewModel: FavouriteViewModel by viewModels()
}

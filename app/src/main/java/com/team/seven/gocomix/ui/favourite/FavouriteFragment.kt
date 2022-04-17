package com.team.seven.gocomix.ui.favourite

import androidx.fragment.app.viewModels
import com.team.seven.gocomix.R
import com.team.seven.gocomix.databinding.FragmentFavouriteBinding
import com.team.seven.gocomix.ui.AbstractFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteFragment : AbstractFragment<FragmentFavouriteBinding, FavouriteViewModel>(
    layoutRes = R.layout.fragment_favourite
) {

    override val viewModel: FavouriteViewModel by viewModels()
}

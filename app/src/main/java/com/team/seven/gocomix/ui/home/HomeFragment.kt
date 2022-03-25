package com.team.seven.gocomix.ui.home

import androidx.fragment.app.viewModels
import com.team.seven.gocomix.R
import com.team.seven.gocomix.databinding.FragmentHomeBinding
import com.team.seven.gocomix.ui.AbstractFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : AbstractFragment<FragmentHomeBinding>(
    R.layout.fragment_home
) {

    private val viewModel: HomeViewModel by viewModels()
}

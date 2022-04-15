package com.team.seven.gocomix.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.team.seven.gocomix.R
import com.team.seven.gocomix.databinding.FragmentHomeBinding
import com.team.seven.gocomix.ui.AbstractFragment
import com.team.seven.gocomix.ui.home.adapter.ComicsPreviewAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : AbstractFragment<FragmentHomeBinding, HomeViewModel>(
    R.layout.fragment_home
) {

    override val viewModel: HomeViewModel by viewModels()

    private val comicsAdapter = ComicsPreviewAdapter()

    override fun onBindingCreated() {
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.comicsRecyclerView)
        binding.comicsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = comicsAdapter
        }
    }

    override suspend fun onCollectStates() {
        viewModel.comicsState.collect { comicsState ->
            when (comicsState) {
                is ComicsUiState.Success -> {
                    comicsAdapter.submitList(comicsState.comics)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.updateComics()
    }
}

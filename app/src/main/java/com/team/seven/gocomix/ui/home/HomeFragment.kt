package com.team.seven.gocomix.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.team.seven.gocomix.R
import com.team.seven.gocomix.databinding.FragmentHomeBinding
import com.team.seven.gocomix.model.Comix
import com.team.seven.gocomix.ui.AbstractFragment
import com.team.seven.gocomix.ui.home.adapter.ComicsPreviewAdapter
import com.team.seven.gocomix.util.expand
import com.team.seven.gocomix.util.hide
import com.team.seven.gocomix.util.setOnSlide
import com.team.seven.gocomix.util.setOnStateChange
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : AbstractFragment<FragmentHomeBinding, HomeViewModel>(
    layoutRes = R.layout.fragment_home
) {

    override val viewModel: HomeViewModel by viewModels()

    private val bottomSheetBehavior by lazy {
        BottomSheetBehavior.from(
            binding.comicInfoBottomSheet.comixInfoLayout
        )
    }

    private val comicsAdapter = ComicsPreviewAdapter().apply {
        interestListener = {
            openBottomSheet(it)
        }
    }

    override fun onBindingCreated() {
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.comicsRecyclerView)
        binding.apply {
            comicsRecyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = comicsAdapter
            }
            comicsBottomSheetBackground.apply {
                setOnClickListener {
                    bottomSheetBehavior.hide()
                }
                isClickable = false
            }
        }
        handleBottomSheetState()
    }

    override suspend fun onCollectStates() {
        viewModel.comicsState.collect {
            handleComicsState(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.updateComics()
    }

    private fun handleBottomSheetState() {
        bottomSheetBehavior.apply {
            setOnStateChange { _, state ->
                binding.comicsBottomSheetBackground.apply {
                    isClickable = state != BottomSheetBehavior.STATE_HIDDEN
                }
            }
            setOnSlide { _, slideOffset ->
                binding.comicsBottomSheetBackground.alpha = slideOffset
            }
            bottomSheetBehavior.hide()
        }
    }

    private fun handleComicsState(comicsState: ComicsUiState) {
        when (comicsState) {
            is ComicsUiState.Loading -> {
                binding.comicsProgressIndicator.isIndeterminate = true
            }
            is ComicsUiState.Success -> {
                binding.comicsProgressIndicator.isIndeterminate = false
                comicsAdapter.submitList(comicsState.comics)
            }
            is ComicsUiState.Error -> {
            }
        }
    }

    private fun openBottomSheet(comic: Comix) {
        binding.comicInfoBottomSheet.comic = comic
        binding.executePendingBindings()
        bottomSheetBehavior.expand()
    }
}

package com.team.seven.gocomix.ui.comics.home

import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.PagerSnapHelper
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.snackbar.Snackbar
import com.team.seven.gocomix.R
import com.team.seven.gocomix.data.entity.Comic
import com.team.seven.gocomix.databinding.FragmentHomeBinding
import com.team.seven.gocomix.ui.AbstractFragment
import com.team.seven.gocomix.ui.UiState
import com.team.seven.gocomix.ui.comics.home.adapter.ComicsPreviewAdapter
import com.team.seven.gocomix.util.expand
import com.team.seven.gocomix.util.hide
import com.team.seven.gocomix.util.setOnSlide
import com.team.seven.gocomix.util.setOnStateChange
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : AbstractFragment<FragmentHomeBinding, HomeViewModel>(
    layoutRes = R.layout.fragment_home
) {

    override val viewModel by navGraphViewModels<HomeViewModel>(R.id.nav_graph_xml) {
        defaultViewModelProviderFactory
    }

    private val bottomSheetBehavior by lazy {
        BottomSheetBehavior.from(
            binding.comicInfoBottomSheet.comixInfoLayout
        )
    }

    private val comicsAdapter = ComicsPreviewAdapter().apply {
        infoListener = {
            openInfoBottomSheet(it)
        }
        readListener = {
            openComic(it)
        }
    }

    override fun onBindingCreated() {
        attachPagerSnapHelper()
        binding.apply {
            comicsRecyclerView.adapter = comicsAdapter
            comicsBottomSheetBackground.apply {
                setOnClickListener {
                    bottomSheetBehavior.hide()
                }
                isClickable = false
            }
            comicInfoBottomSheet.saveListener = {
                viewModel.saveToFavourites(it)
                val saved = getString(R.string.comic_saved)
                Snackbar.make(requireView(), saved, Snackbar.LENGTH_SHORT)
                    .show()
            }
        }
        bottomSheetBehavior.apply {
            setOnStateChange { _, state ->
                handleBottomSheetState(state)
            }
            setOnSlide { _, slideOffset ->
                handleBottomSheetSlide(slideOffset)
            }
            hide()
        }
    }

    override suspend fun onCollectStates() {
        viewModel.comicsState.collect {
            handleComicsState(it)
        }
    }

    private fun attachPagerSnapHelper() {
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.comicsRecyclerView)
    }

    private fun handleBottomSheetState(state: Int) {
        binding.comicsBottomSheetBackground.apply {
            isClickable = state != BottomSheetBehavior.STATE_HIDDEN
        }
    }

    private fun handleBottomSheetSlide(slideOffset: Float) {
        binding.comicsBottomSheetBackground.alpha = slideOffset
    }

    private fun handleComicsState(comicsState: UiState<List<Comic>>) {
        when (comicsState) {
            is UiState.Loading -> {
                binding.comicsProgressIndicator.isIndeterminate = true
            }
            is UiState.Success -> {
                binding.comicsProgressIndicator.isIndeterminate = false
                comicsAdapter.submitList(comicsState.value)
            }
            is UiState.Failure -> {
            }
        }
    }

    private fun openInfoBottomSheet(comic: Comic) {
        binding.apply {
            comicInfoBottomSheet.comic = comic
            executePendingBindings()
        }
        bottomSheetBehavior.expand()
    }

    private fun openComic(comic: Comic) {
        val action = HomeFragmentDirections
            .actionHomeToPages(comic.id)
        navController.navigate(action)
    }
}

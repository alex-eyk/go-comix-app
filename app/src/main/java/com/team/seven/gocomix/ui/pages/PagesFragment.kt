package com.team.seven.gocomix.ui.pages

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.team.seven.gocomix.R
import com.team.seven.gocomix.data.entity.Page
import com.team.seven.gocomix.databinding.FragmentComixPagesBinding
import com.team.seven.gocomix.ui.AbstractFragment
import com.team.seven.gocomix.ui.UiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PagesFragment : AbstractFragment<FragmentComixPagesBinding, PagesViewModel>(
    layoutRes = R.layout.fragment_comix_pages
) {

    override val viewModel: PagesViewModel by viewModels()

    private val navArgs by navArgs<PagesFragmentArgs>()

    private val pagesAdapter = PagesAdapter().apply {
        loadedListener = {
            viewModel.onImageLoaded(it)
        }
    }

    override fun onBindingCreated() {
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.comixPageImagesRecyclerView)
        binding.apply {
            comixPageImagesRecyclerView?.apply {
                layoutManager = horizontalLayoutManager()
                adapter = pagesAdapter
            }
        }
        binding.comixPageImagesRecyclerView
    }

    override suspend fun onCollectStates() {
        viewModel.pagesState.collect {
            handlePagesState(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.updatePages(navArgs.comicId)
    }

    private fun horizontalLayoutManager(): RecyclerView.LayoutManager {
        return LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
    }

    private fun handlePagesState(pagesState: UiState<List<Page>>) {
        when (pagesState) {
            is UiState.Loading -> {
            }
            is UiState.Success -> {
                pagesAdapter.submitList(pagesState.value)
            }
            is UiState.Failure -> {
            }
        }
    }
}

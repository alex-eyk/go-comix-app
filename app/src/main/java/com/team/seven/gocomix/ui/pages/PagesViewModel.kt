package com.team.seven.gocomix.ui.pages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team.seven.gocomix.data.entity.Page
import com.team.seven.gocomix.data.repo.comic.ComicsRepository
import com.team.seven.gocomix.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class PagesViewModel @Inject constructor(
    private val comicsRepository: ComicsRepository
) : ViewModel() {

    private val _pagesState: MutableStateFlow<UiState<List<Page>>> =
        MutableStateFlow(UiState.Loading)

    val pagesState: StateFlow<UiState<List<Page>>> = _pagesState

    fun updatePages(comicId: Int) {
        viewModelScope.launch {
            _pagesState.value = UiState.by(comicsRepository.getPages(comicId))
        }
    }
}

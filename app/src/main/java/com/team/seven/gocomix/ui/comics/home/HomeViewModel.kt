package com.team.seven.gocomix.ui.comics.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team.seven.gocomix.data.entity.Comic
import com.team.seven.gocomix.data.repo.comic.ComicsRepository
import com.team.seven.gocomix.data.repo.comic.FavouriteComicsRepository
import com.team.seven.gocomix.model.Comix
import com.team.seven.gocomix.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val comicsRepository: ComicsRepository,
    private val favComicsRepository: FavouriteComicsRepository
) : ViewModel() {

    private val _comicsState: MutableStateFlow<UiState<List<Comic>>> =
        MutableStateFlow(UiState.Loading)

    val comicsState: StateFlow<UiState<List<Comic>>> = _comicsState

    init {
        updateComics()
    }

    fun updateComics() {
        viewModelScope.launch(Dispatchers.IO) {
            comicsRepository.getRecommendedComics().let {
                _comicsState.value = UiState.by(it)
            }
        }
    }

    fun saveToFavourites(comic: Comic) {
        viewModelScope.launch {
            favComicsRepository.save(comic)
        }
    }
}

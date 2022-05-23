package com.team.seven.gocomix.ui.comics.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team.seven.gocomix.data.entity.Comic
import com.team.seven.gocomix.data.repo.comic.impl.FavouriteComicsLocalRepository
import com.team.seven.gocomix.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val favComicsRepo: FavouriteComicsLocalRepository
) : ViewModel() {

    private val _favouriteComicsState: MutableStateFlow<UiState<List<Comic>>> =
        MutableStateFlow(UiState.Loading)

    val favouriteComicsState: StateFlow<UiState<List<Comic>>> = _favouriteComicsState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _favouriteComicsState.value = UiState.by(
                favComicsRepo.getAll()
            )
        }
    }
}

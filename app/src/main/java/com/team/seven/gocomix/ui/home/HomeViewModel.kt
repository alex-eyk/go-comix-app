package com.team.seven.gocomix.ui.home

import android.os.Parcelable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team.seven.gocomix.data.entity.Comic
import com.team.seven.gocomix.model.Comix
import com.team.seven.gocomix.repo.ComixRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val comixRepository: ComixRepository
) : ViewModel() {

    var listState: Parcelable? = null

    private val _comicsState: MutableStateFlow<ComicsUiState> =
        MutableStateFlow(ComicsUiState.Loading)

    val comicsState: StateFlow<ComicsUiState> = _comicsState

    fun updateComics() {
        viewModelScope.launch(Dispatchers.IO) {
            comixRepository.getComics().let {
                _comicsState.value = ComicsUiState.by(it)
            }
        }
    }

    fun saveToFavourites(comix: Comix) {

    }

    fun saveToFavourites(comic: Comic) {

    }
}

sealed class ComicsUiState {

    object Loading : ComicsUiState()
    data class Success(val comics: List<Comix>) : ComicsUiState()
    data class Error(val exception: Throwable) : ComicsUiState()

    companion object {
        fun by(result: Result<List<Comix>>): ComicsUiState {
            return result.map {
                Success(it)
            }.getOrElse {
                Error(it)
            }
        }
    }
}

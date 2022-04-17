package com.team.seven.gocomix.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    private val _comicsState: MutableStateFlow<ComicsUiState> =
        MutableStateFlow(ComicsUiState.Loading)

    val comicsState: StateFlow<ComicsUiState> = _comicsState

    fun updateComics() {
        viewModelScope.launch(Dispatchers.IO) {
            comixRepository.getComics().let {
                _comicsState.value = ComicsUiState.Success(it)
            }
        }
    }
}

sealed class ComicsUiState {
    object Loading : ComicsUiState()
    data class Success(val comics: List<Comix>) : ComicsUiState()
    data class Error(val exception: Throwable) : ComicsUiState()
}

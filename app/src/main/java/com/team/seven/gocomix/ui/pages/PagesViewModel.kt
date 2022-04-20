package com.team.seven.gocomix.ui.pages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team.seven.gocomix.model.Page
import com.team.seven.gocomix.repo.ComixRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PagesViewModel @Inject constructor(
    private val comixRepository: ComixRepository
) : ViewModel() {

    private val _pagesState: MutableStateFlow<PagesState> = MutableStateFlow(PagesState.Loading)

    val pagesState: StateFlow<PagesState> = _pagesState

    fun updatePages(comicId: Int) {
        viewModelScope.launch {
            _pagesState.value = PagesState.by(comixRepository.getPages(comicId))
        }
    }
}

sealed class PagesState {

    data class Success(val pages: List<Page>) : PagesState()
    data class Error(val error: Throwable) : PagesState()
    object Loading : PagesState()

    companion object {
        fun by(result: Result<List<Page>>): PagesState {
            return result.map {
                Success(it)
            }.getOrElse {
                Error(it)
            }
        }
    }
}

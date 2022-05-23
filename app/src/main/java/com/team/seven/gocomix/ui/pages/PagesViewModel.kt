package com.team.seven.gocomix.ui.pages

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team.seven.gocomix.data.entity.Page
import com.team.seven.gocomix.data.repo.comic.ComicsRepository
import com.team.seven.gocomix.domaim.interactor.TranslateRecognizeUseCase
import com.team.seven.gocomix.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class PagesViewModel @Inject constructor(
    private val comicsRepository: ComicsRepository,
    private val translateRecognizeUseCase: TranslateRecognizeUseCase
) : ViewModel() {

    private val _pagesState: MutableStateFlow<UiState<List<Page>>> =
        MutableStateFlow(UiState.Loading)

    private val _translatedState: MutableStateFlow<UiState<String>> =
        MutableStateFlow(UiState.None)

    val pagesState: StateFlow<UiState<List<Page>>> = _pagesState
    val translatedState: StateFlow<UiState<String>> = _translatedState

    fun updatePages(comicId: Int) {
        viewModelScope.launch {
            _pagesState.value = UiState.by(comicsRepository.getPages(comicId))
        }
    }

    fun onImageLoaded(image: Bitmap) {
        viewModelScope.launch {
            _translatedState.value = UiState.by(translateRecognizeUseCase.execute(image))
        }
    }
}

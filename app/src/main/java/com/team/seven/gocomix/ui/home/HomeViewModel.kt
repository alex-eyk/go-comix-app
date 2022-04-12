package com.team.seven.gocomix.ui.home

import androidx.lifecycle.ViewModel
import com.team.seven.gocomix.repo.ComixRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val comixRepository: ComixRepository
) : ViewModel()

package com.team.seven.gocomix.ui.profileEdit

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileEditViewModel @Inject constructor() : ViewModel() {

    fun edit() {
        Log.d(TAG, "edit")
    }
}

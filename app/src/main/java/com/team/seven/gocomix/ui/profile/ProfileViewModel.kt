package com.team.seven.gocomix.ui.profile

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {
    fun signOut() {
        Log.d(ContentValues.TAG, "signOut")
    }
}

package com.team.seven.gocomix.ui.profile

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.team.seven.gocomix.data.entity.Comic
import com.team.seven.gocomix.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.lang.NullPointerException
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _email: MutableStateFlow<String> = MutableStateFlow("")
    val email: StateFlow<String> = _email

    init {
        val email = firebaseAuth.currentUser?.email
        _email.value = email ?: ""
    }

    fun signOut() {
        firebaseAuth.signOut()
    }
}

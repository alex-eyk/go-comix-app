package com.team.seven.gocomix.ui.signin

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.team.seven.gocomix.ui.splash.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _userState: MutableStateFlow<AuthState> = MutableStateFlow(AuthState.Loading)

    val userState: StateFlow<AuthState> = _userState

    fun updateAuthState() {
        _userState.value = AuthState.by(firebaseAuth.currentUser)
    }
}


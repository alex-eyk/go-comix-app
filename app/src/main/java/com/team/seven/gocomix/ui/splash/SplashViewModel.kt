package com.team.seven.gocomix.ui.splash

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _userState: MutableStateFlow<AuthState> = MutableStateFlow(AuthState.Loading)

    val userState: StateFlow<AuthState> = _userState

    fun updateAuthState() {
        _userState.value = AuthState.by(firebaseAuth.currentUser)
    }
}

sealed class AuthState {

    data class Logged(val user: FirebaseUser) : AuthState()
    object NotLogged : AuthState()
    object Loading : AuthState()

    companion object {
        fun by(user: FirebaseUser?): AuthState {
            return if (user != null) {
                Logged(user)
            } else {
                NotLogged
            }
        }
    }
}

package com.team.seven.gocomix.ui.auth.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.team.seven.gocomix.ui.auth.exception.EmptyEmailException
import com.team.seven.gocomix.ui.auth.exception.EmptyPasswordException
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _signInState: MutableStateFlow<SignInState> =
        MutableStateFlow(SignInState.Loading)

    val signInState: StateFlow<SignInState> = _signInState

    val email = MutableStateFlow("")
    val password = MutableStateFlow("")

    fun signInWithEmail() {
        when {
            email.value.isEmpty() -> {
                _signInState.value = SignInState.Failure(EmptyEmailException())
            }
            password.value.isEmpty() -> {
                _signInState.value = SignInState.Failure(EmptyPasswordException())
            }
            else -> {
                viewModelScope.launch(Dispatchers.IO) {
                    firebaseAuth.signInWithEmailAndPassword(
                        email.value,
                        password.value
                    )
                        .addOnCompleteListener {
                            _signInState.value = SignInState.by(it)
                        }
                        .addOnFailureListener {
                            _signInState.value = SignInState.Failure(it)
                        }
                }
            }
        }
    }
}

sealed class SignInState {

    object Loading : SignInState()
    object Success : SignInState()
    data class Failure(val e: Throwable) : SignInState()

    companion object {

        fun by(task: Task<AuthResult>): SignInState {
            return when {
                task.isSuccessful -> Success
                task.exception != null -> Failure(task.exception!!)
                else -> Failure(IllegalStateException())
            }
        }
    }
}

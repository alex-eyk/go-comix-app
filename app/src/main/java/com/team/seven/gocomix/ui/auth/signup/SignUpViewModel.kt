package com.team.seven.gocomix.ui.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.team.seven.gocomix.ui.auth.exception.DifPasswordConfirmException
import com.team.seven.gocomix.ui.auth.exception.EmptyEmailException
import com.team.seven.gocomix.ui.auth.exception.EmptyPasswordConfirmException
import com.team.seven.gocomix.ui.auth.exception.EmptyPasswordException
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _signUpState: MutableStateFlow<SignUpState> =
        MutableStateFlow(SignUpState.Loading)

    val signUpState: StateFlow<SignUpState> = _signUpState

    val email = MutableStateFlow("")
    val password = MutableStateFlow("")
    val passwordConfirm = MutableStateFlow("")

    fun signInWithEmail() {
        when {
            email.value.isEmpty() -> {
                _signUpState.value = SignUpState.Failure(EmptyEmailException())
            }
            password.value.isEmpty() -> {
                _signUpState.value = SignUpState.Failure(EmptyPasswordException())
            }
            passwordConfirm.value.isEmpty() -> {
                _signUpState.value = SignUpState.Failure(EmptyPasswordConfirmException())
            }
            passwordConfirm.value != password.value -> {
                _signUpState.value = SignUpState.Failure(DifPasswordConfirmException())
            }
            else -> {
                viewModelScope.launch(Dispatchers.IO) {
                    firebaseAuth.createUserWithEmailAndPassword(
                        email.value,
                        password.value
                    )
                        .addOnCompleteListener {
                            _signUpState.value = SignUpState.by(it)
                        }
                        .addOnFailureListener {
                            _signUpState.value = SignUpState.Failure(it)
                        }
                }
            }
        }
    }
}

sealed class SignUpState {

    object Loading : SignUpState()
    object Success : SignUpState()
    data class Failure(val e: Throwable) : SignUpState()

    companion object {

        fun by(task: Task<AuthResult>): SignUpState {
            return when {
                task.isSuccessful -> Success
                task.exception != null -> Failure(task.exception!!)
                else -> Failure(IllegalStateException())
            }
        }
    }
}
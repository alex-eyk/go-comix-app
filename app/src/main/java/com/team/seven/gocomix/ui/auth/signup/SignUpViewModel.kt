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
import com.team.seven.gocomix.ui.auth.exception.ShortPasswordException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    companion object {
        private const val MIN_PASSWORD_LENGTH = 6
    }

    private val _signUpState: MutableStateFlow<SignUpState> =
        MutableStateFlow(SignUpState.Loading)

    val signUpState: StateFlow<SignUpState> = _signUpState

    val email = MutableStateFlow("")
    val password = MutableStateFlow("")
    val passwordConfirm = MutableStateFlow("")

    fun signUpWithEmail() {
        when {
            email.value.isEmpty() -> {
                _signUpState.value = SignUpState.Failure(EmptyEmailException())
            }
            password.value.isEmpty() -> {
                _signUpState.value = SignUpState.Failure(EmptyPasswordException())
            }
            password.value.length < MIN_PASSWORD_LENGTH -> {
                _signUpState.value = SignUpState.Failure(ShortPasswordException())
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

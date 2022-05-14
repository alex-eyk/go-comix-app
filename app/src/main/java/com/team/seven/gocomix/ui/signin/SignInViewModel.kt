package com.team.seven.gocomix.ui.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _signInState: MutableStateFlow<SignInState> =
        MutableStateFlow(SignInState.Loading)

   val signInState: StateFlow<SignInState> = _signInState

    fun signIn(email: String, pasw: String){
        viewModelScope.launch(Dispatchers.IO) {
                firebaseAuth.signInWithEmailAndPassword(email, pasw)
                    .addOnCompleteListener{
                            task-> onCompleteSignInTask(task)
                    }
            }
    }
    fun onCompleteSignInTask(task: Task<AuthResult>){
        _signInState.value= SignInState.by(task)
    }
}

sealed class SignInState {

    object Loading: SignInState()
    object Success : SignInState()
    data class Error(val exception: Throwable) : SignInState()

    companion object {
        fun by(task: Task<AuthResult>): SignInState {
            if (task.isSuccessful) {
                return Success
            }
            else {
                return SignInState.Error(task.exception!!)
            }
        }
    }
}

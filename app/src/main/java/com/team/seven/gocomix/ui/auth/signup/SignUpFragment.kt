package com.team.seven.gocomix.ui.auth.signup

import android.content.ContentValues
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.core.widget.addTextChangedListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWebException
import com.team.seven.gocomix.R
import com.team.seven.gocomix.databinding.FragmentSignUpBinding
import com.team.seven.gocomix.ui.auth.AbstractAuthFragment
import com.team.seven.gocomix.ui.auth.exception.EmptyEmailException
import com.team.seven.gocomix.ui.auth.exception.EmptyPasswordException
import com.team.seven.gocomix.ui.auth.exception.EmptyPasswordConfirmException
import com.team.seven.gocomix.ui.auth.exception.DifPasswordConfirmException
import com.team.seven.gocomix.ui.auth.exception.ShortPasswordException
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : AbstractAuthFragment<FragmentSignUpBinding, SignUpViewModel>(
    layoutRes = R.layout.fragment_sign_up
) {

    companion object {
        private const val TAG = "SignUpFragment"
    }

    override val viewModel: SignUpViewModel by viewModels()

    override fun onBindingCreated() {
        super.onBindingCreated()
        binding.viewModel = viewModel
        binding.apply {
            registerEmailEditText.addTextChangedListener{
                registerEmailInputLayout.error = null
            }
            registerPasswordEditText.addTextChangedListener {
                registerPasswordInputLayout.error = null
            }
            loginPasswordAgainEditText.addTextChangedListener {
                registerPasswordAgainInputLayout.error = null
            }
        }
    }

    override suspend fun onCollectStates() {
        viewModel.signUpState.collect {
            handleSignUpState(it)
        }
    }

    private fun handleSignUpState(state: SignUpState) {
        when (state) {
            is SignUpState.Loading -> {
            }
            is SignUpState.Success -> {
                navController.navigate(R.id.navigation_sign_in)
            }
            is SignUpState.Failure -> {
                handleException(state.e)
            }
        }
    }

    private fun handleException(e: Throwable) {
        when (e) {
            is EmptyEmailException -> {
                binding.registerEmailInputLayout.error = resources.getString(
                    R.string.input_field_is_empty_error
                )
            }
            is EmptyPasswordException -> {
                binding.registerPasswordInputLayout.error = resources.getString(
                    R.string.input_field_is_empty_error
                )
            }
            is EmptyPasswordConfirmException -> {
                binding.registerPasswordAgainInputLayout.error = resources.getString(
                    R.string.input_field_is_empty_error
                )
            }
            is DifPasswordConfirmException -> {
                binding.registerPasswordAgainInputLayout.error = resources.getString(
                    R.string.different_password
                )
            }
            is FirebaseAuthWebException -> {
                Snackbar.make(requireView(), "Ошибка, попробуйте зарегестрироваться немного позже",
                    Snackbar.LENGTH_SHORT).show()
            }
            is FirebaseAuthUserCollisionException -> {
                Snackbar.make(requireView(), "Пользователь с таким логином уже существует",
                    Snackbar.LENGTH_SHORT).show()
            }
            is FirebaseAuthRecentLoginRequiredException -> {
                Snackbar.make(requireView(), "Ошибка, попробуйте зарегистрироваться ещё раз",
                    Snackbar.LENGTH_SHORT).show()
            }
            is ShortPasswordException -> {
                binding.registerPasswordInputLayout.error = resources.getString(
                    R.string.short_password
                )
            }
            else -> {
                Snackbar.make(requireView(),"Ошибка", Snackbar.LENGTH_SHORT).show()
                Log.d(e.message, "e.message")
            }
        }
    }
}

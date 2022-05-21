package com.team.seven.gocomix.ui.auth.signup

import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.team.seven.gocomix.R
import com.team.seven.gocomix.databinding.FragmentSignUpBinding
import com.team.seven.gocomix.ui.auth.AbstractAuthFragment
import com.team.seven.gocomix.ui.auth.signup.exception.EmptyEmailException
import com.team.seven.gocomix.ui.auth.signup.exception.EmptyPasswordException
import com.team.seven.gocomix.ui.auth.signup.exception.EmptyPasswordConfirmException
import com.team.seven.gocomix.ui.auth.signup.exception.DifPasswordConfirmException
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : AbstractAuthFragment<FragmentSignUpBinding, SignUpViewModel>(
    layoutRes = R.layout.fragment_sign_in
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
            else -> {
                Snackbar.make(requireView(), resources.getString(
                    R.string.unknown_error
                ), Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}

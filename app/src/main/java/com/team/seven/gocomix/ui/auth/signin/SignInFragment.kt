package com.team.seven.gocomix.ui.auth.signin

import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException
import com.google.firebase.auth.FirebaseAuthWebException
import com.team.seven.gocomix.R
import com.team.seven.gocomix.databinding.FragmentSignInBinding
import com.team.seven.gocomix.ui.auth.AbstractAuthFragment
import com.team.seven.gocomix.ui.auth.exception.EmptyEmailException
import com.team.seven.gocomix.ui.auth.exception.EmptyPasswordException
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : AbstractAuthFragment<FragmentSignInBinding, SignInViewModel>(
    layoutRes = R.layout.fragment_sign_in
) {

    companion object {
        private const val TAG = "SignInFragment"
    }

    override val viewModel: SignInViewModel by viewModels()

    override fun onBindingCreated() {
        super.onBindingCreated()
        binding.viewModel = viewModel
        binding.apply {
            loginUsernameEditText.addTextChangedListener {
                loginUsernameInputLayout.error = null
            }
            loginPasswordEditText.addTextChangedListener {
                loginPasswordInputLayout.error = null
            }
        }
        binding.loginRegisterButton.setOnClickListener {
            navController.navigate(R.id.navigation_sign_up)
        }
    }

    override suspend fun onCollectStates() {
        viewModel.signInState.collect {
            handleSignInState(it)
        }
    }

    private fun handleSignInState(state: SignInState) {
        when (state) {
            is SignInState.Loading -> {
            }
            is SignInState.Success -> {
                navController.navigate(R.id.navigation_home)
            }
            is SignInState.Failure -> {
                handleException(state.e)
            }
        }
    }

    private fun handleException(e: Throwable) {
        when (e) {
            is EmptyEmailException -> {
                binding.loginUsernameInputLayout.error = resources.getString(
                    R.string.input_field_is_empty_error
                )
            }
            is EmptyPasswordException -> {
                binding.loginPasswordInputLayout.error = resources.getString(
                    R.string.input_field_is_empty_error
                )
            }
            is FirebaseAuthInvalidCredentialsException -> {
                Snackbar.make(
                    requireView(),
                    "???????????? ??????????, ?????????????????????????? ?????????????????? ????????????",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
            is FirebaseAuthInvalidUserException -> {
                Snackbar.make(
                    requireView(),
                    "???????????????????????? ?? ???????????? ?????????????? ????????????",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
            is FirebaseAuthRecentLoginRequiredException -> {
                Snackbar.make(
                    requireView(),
                    "????????????, ???????????????????? ???????????? ?????????????? ?????? ??????",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
            is FirebaseAuthWebException -> {
                Snackbar.make(
                    requireView(),
                    "????????????, ???????????????????? ?????????? ?????????????? ??????????",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
            else -> {
                Snackbar.make(requireView(), "????????????", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}

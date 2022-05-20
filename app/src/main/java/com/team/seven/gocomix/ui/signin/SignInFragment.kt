package com.team.seven.gocomix.ui.signin

import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.team.seven.gocomix.R
import com.team.seven.gocomix.databinding.FragmentSignInBinding
import com.team.seven.gocomix.ui.AbstractFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : AbstractFragment<FragmentSignInBinding, SignInViewModel>(
    layoutRes = R.layout.fragment_sign_in
) {

    companion object {
        private const val TAG = "SignInFragment"
    }

    private val navBar: BottomNavigationView by lazy {
        requireActivity().findViewById(R.id.bottom_navigation_view)
    }

    override val viewModel: SignInViewModel by viewModels()

    override fun onBindingCreated() {
        super.binding.loginLoginButton.setOnClickListener {
            viewModel.signInWithEmail(
                binding.loginUsernameEditText.text.toString(),
                binding.loginPasswordEditText.text.toString()
            )
        }
        navBar.visibility = View.GONE
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
            is SignInState.Error -> {
                Log.e(TAG, "Unable to sign in", state.exception)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        navBar.visibility = View.VISIBLE
    }
}

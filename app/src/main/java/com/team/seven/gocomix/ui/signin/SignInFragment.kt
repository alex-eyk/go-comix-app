package com.team.seven.gocomix.ui.signin

import androidx.fragment.app.viewModels
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

    override val viewModel: SignInViewModel by viewModels()

    override fun onBindingCreated() {
        super.binding.loginLoginButton.setOnClickListener {
        }
        super.binding.loginRegisterButton.setOnClickListener {
            navController.navigate(R.id.navigation_sign_up)
        }
    }
}

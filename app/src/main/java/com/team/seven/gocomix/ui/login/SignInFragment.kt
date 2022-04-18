package com.team.seven.gocomix.ui.login

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

    override val viewModel: SignInViewModel by viewModels()

    override fun onBindingCreated() {
        super.binding.loginLoginButton.setOnClickListener {
            navController.navigate(R.id.action_navigation_sign_in_to_navigation_home)
        }
        val navBar: BottomNavigationView = requireActivity()
            .findViewById(R.id.bottom_navigation_view)
        navBar.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        val navBar: BottomNavigationView = requireActivity()
            .findViewById(R.id.bottom_navigation_view)
        navBar.visibility = View.VISIBLE
    }
}

package com.team.seven.gocomix.ui.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.team.seven.gocomix.R
import com.team.seven.gocomix.databinding.FragmentSplashBinding
import com.team.seven.gocomix.ui.AbstractFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : AbstractFragment<FragmentSplashBinding, SplashViewModel>(
    layoutRes = R.layout.fragment_splash
) {

    override val viewModel: SplashViewModel by viewModels()

    override suspend fun onCollectStates() {
        viewModel.userState.collect { state ->
            when (state) {
                is AuthState.Loading -> {
                }
                is AuthState.Logged -> {
                    navController.navigate(R.id.action_splash_to_home)
                }
                is AuthState.NotLogged -> {
                    navController.navigate(R.id.action_splash_to_sign_in)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.updateAuthState()
    }
}

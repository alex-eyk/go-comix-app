package com.team.seven.gocomix.ui.profile

import androidx.fragment.app.viewModels
import com.firebase.ui.auth.AuthUI
import com.team.seven.gocomix.R
import com.team.seven.gocomix.databinding.FragmentProfileBinding
import com.team.seven.gocomix.ui.AbstractFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : AbstractFragment<FragmentProfileBinding, ProfileViewModel>(
    layoutRes = R.layout.fragment_profile
) {

    override val viewModel: ProfileViewModel by viewModels()

    override fun onBindingCreated() {
        super.onBindingCreated()
        binding.profileEditButton.setOnClickListener {
            navController.navigate(R.id.action_navigation_profile_to_profileEditFragment)
        }
        binding.profileLogoutButton.setOnClickListener {
            AuthUI.getInstance().signOut(requireContext())
            navController.popBackStack(R.id.navigation_splash, true)
        }
    }
}

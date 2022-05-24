package com.team.seven.gocomix.ui.profile

import androidx.fragment.app.viewModels
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
        binding.viewModule = viewModel
        binding.profileEditButton.setOnClickListener {
            navController.navigate(R.id.action_profile_to_edit)
        }
        binding.profileLogoutButton.setOnClickListener {
            navController.navigate(R.id.action_profile_to_sign_in)
            viewModel.signOut()
        }
    }
}

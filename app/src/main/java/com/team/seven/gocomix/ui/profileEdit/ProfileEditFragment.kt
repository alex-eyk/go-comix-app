package com.team.seven.gocomix.ui.profileEdit

import androidx.fragment.app.viewModels
import com.team.seven.gocomix.R
import com.team.seven.gocomix.databinding.ProfileEditFragmentBinding
import com.team.seven.gocomix.ui.AbstractFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileEditFragment : AbstractFragment<ProfileEditFragmentBinding, ProfileEditViewModel>(
    R.layout.profile_edit_fragment
) {

    override val viewModel: ProfileEditViewModel by viewModels()

    override fun onBindingCreated() {
        super.onBindingCreated()
        binding.profileEditCloseButton.setOnClickListener {
            navController.navigate(R.id.action_profileEditFragment_to_navigation_profile)
        }
        binding.profileEditSaveButton.setOnClickListener {
            navController.navigate(R.id.action_profileEditFragment_to_navigation_profile)
            viewModel.edit()
        }
    }
}

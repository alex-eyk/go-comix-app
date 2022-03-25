package com.team.seven.gocomix.ui.profile

import androidx.fragment.app.viewModels
import com.team.seven.gocomix.R
import com.team.seven.gocomix.databinding.FragmentProfileBinding
import com.team.seven.gocomix.ui.AbstractFragment

class ProfileFragment : AbstractFragment<FragmentProfileBinding>(
    R.layout.fragment_profile
) {

    private val viewModel: ProfileViewModel by viewModels()
}

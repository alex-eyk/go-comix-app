package com.team.seven.gocomix.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.team.seven.gocomix.R
import com.team.seven.gocomix.databinding.FragmentProfileBinding
import com.team.seven.gocomix.ui.AbstractFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : AbstractFragment<FragmentProfileBinding, ProfileViewModel>(
    R.layout.fragment_profile
) {

    override val viewModel: ProfileViewModel by viewModels()
    private var _profileBinding: FragmentProfileBinding? = null

    private val profileBinding get() = _profileBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _profileBinding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = profileBinding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileBinding.profileEditButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_navigation_profile_to_profileEditFragment)
        }
    }

}

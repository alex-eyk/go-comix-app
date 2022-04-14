package com.team.seven.gocomix.ui.profile_edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.team.seven.gocomix.R
import com.team.seven.gocomix.databinding.FragmentProfileEditBinding
import com.team.seven.gocomix.ui.AbstractFragment
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class ProfileEditFragment : AbstractFragment<FragmentProfileEditBinding, ProfileEditViewModel>(
    R.layout.profile_edit_fragment
) {

    override val viewModel: ProfileEditViewModel by viewModels()
    private var _profileEditBinding: FragmentProfileEditBinding? = null

    private val profileEditBinding get() = _profileEditBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _profileEditBinding = FragmentProfileEditBinding.inflate(inflater, container, false)
        val view = profileEditBinding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileEditBinding.profileEditCloseButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_profileEditFragment_to_navigation_profile)
        }
    }

}



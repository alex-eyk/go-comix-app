package com.team.seven.gocomix.ui.signup

import androidx.fragment.app.viewModels
import com.team.seven.gocomix.R
import com.team.seven.gocomix.databinding.FragmentSignUpBinding
import com.team.seven.gocomix.ui.AbstractFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : AbstractFragment<FragmentSignUpBinding, SignUpViewModel>(
    layoutRes = R.layout.fragment_sign_up
) {

    companion object {
        private const val TAG = "SignUpFragment"
    }

    override val viewModel: SignUpViewModel by viewModels()
}




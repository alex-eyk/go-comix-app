package com.team.seven.gocomix.ui.signup

import androidx.fragment.app.viewModels
import com.google.android.material.textfield.TextInputEditText
import com.team.seven.gocomix.R
import com.team.seven.gocomix.databinding.FragmentSignUpBinding
import com.team.seven.gocomix.ui.AbstractFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : AbstractFragment<FragmentSignUpBinding, SignUpViewModel>(
    layoutRes = R.layout.fragment_sign_up
) {
    lateinit var editText: TextInputEditText
    lateinit var username: String
    lateinit var email: String
    private lateinit var password: String
    private lateinit var pasw: String


    companion object {
        private const val TAG = "SignUpFragment"
    }

    override val viewModel: SignUpViewModel by viewModels()

    override fun onBindingCreated() {
        super.binding.registerUsernameEditText.setOnClickListener {
            username = editText.text.toString()
        }
        super.binding.registerEmailEditText.setOnClickListener {
            email = editText.text.toString()
        }
        super.binding.registerPasswordEditText.setOnClickListener {
            password = editText.text.toString()
        }
        super.binding.loginPasswordAgainEditText.setOnClickListener {
            pasw = editText.text.toString()
        }
        super.binding.btnRegister.setOnClickListener {


        }

    }

}




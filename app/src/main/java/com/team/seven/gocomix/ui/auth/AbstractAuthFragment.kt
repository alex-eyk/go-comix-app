package com.team.seven.gocomix.ui.auth

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.team.seven.gocomix.R
import com.team.seven.gocomix.ui.AbstractFragment

abstract class AbstractAuthFragment<B : ViewDataBinding, VM : ViewModel>(
    layoutRes: Int
) : AbstractFragment<B, VM>(layoutRes) {

    private val navBar: BottomNavigationView by lazy {
        requireActivity().findViewById(R.id.bottom_navigation_view)
    }

    override fun onBindingCreated() {
        super.onBindingCreated()
        navBar.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        navBar.visibility = View.VISIBLE
    }
}

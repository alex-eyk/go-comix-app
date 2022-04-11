package com.team.seven.gocomix.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

abstract class AbstractFragment<B : ViewDataBinding, VM : ViewModel>(
    private val layoutRes: Int
) : Fragment() {

    protected abstract val viewModel: VM

    protected lateinit var binding: B

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.binding = DataBindingUtil
            .inflate(inflater, layoutRes, container, false)
        onBindingCreated()
        return binding.root
    }

    open fun onBindingCreated() {
    }
}

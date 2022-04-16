package com.team.seven.gocomix.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch

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
        collectStates()
        return binding.root
    }

    private fun collectStates() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(
                Lifecycle.State.STARTED
            ) {
                onCollectStates()
            }
        }
    }

    open fun onBindingCreated() {
    }

    open suspend fun onCollectStates() {
    }
}

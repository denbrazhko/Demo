package com.rexbot.bitrtix.bot.ui.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding


abstract class BaseFragment<B : ViewBinding, VM : ViewModel> : Fragment() {

    private var _binding: B? = null
    val binding get() = _binding!!

    lateinit var viewModel: VM


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getViewBinding()
        viewModel = ViewModelProvider(requireActivity()).get(getViewModelClass())
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    abstract fun getViewBinding(): B
    abstract fun init()
    abstract fun initObservers()
    abstract fun getViewModelClass(): Class<VM>

}
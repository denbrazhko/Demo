package com.rexbot.bitrtix.bot.ui.terminal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.rexbot.bitrtix.bot.R
import com.rexbot.bitrtix.bot.databinding.FragmentTerminalBinding
import com.rexbot.bitrtix.bot.ui.common.BaseFragment

class TerminalFragment:BaseFragment<FragmentTerminalBinding, TerminalViewModel>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    override fun getViewModelClass(): Class<TerminalViewModel> = TerminalViewModel::class.java


    override fun getViewBinding(): FragmentTerminalBinding =
        FragmentTerminalBinding.inflate(layoutInflater)

    override fun init() {
        TODO("Not yet implemented")
    }

    override fun initObservers() {
        binding.textView4.setOnClickListener { findNavController().navigate(R.id.action_terminal_to_choose_currency) }
    }
}
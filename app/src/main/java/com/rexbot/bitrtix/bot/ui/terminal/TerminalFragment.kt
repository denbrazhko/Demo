package com.rexbot.bitrtix.bot.ui.terminal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rexbot.bitrtix.bot.databinding.FragmentTerminalBinding
import com.rexbot.bitrtix.bot.ui.common.BaseFragment

class TerminalFragment:BaseFragment<FragmentTerminalBinding, TerminalViewModel>() {

    override fun getViewModelClass(): Class<TerminalViewModel> = TerminalViewModel::class.java


    override fun getViewBinding(): FragmentTerminalBinding =
        FragmentTerminalBinding.inflate(layoutInflater)

    override fun init() {
        TODO("Not yet implemented")
    }

    override fun initObservers() {
        TODO("Not yet implemented")
    }
}
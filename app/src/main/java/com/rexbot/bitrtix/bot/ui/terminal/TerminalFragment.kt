package com.rexbot.bitrtix.bot.ui.terminal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rexbot.bitrtix.bot.databinding.FragmentTerminalBinding
import com.rexbot.bitrtix.bot.ui.common.BaseFragment

class TerminalFragment:BaseFragment<FragmentTerminalBinding>() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }


    override fun getViewBinding(): FragmentTerminalBinding =
        FragmentTerminalBinding.inflate(layoutInflater)
}
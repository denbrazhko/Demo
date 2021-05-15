package com.rexbot.bitrtix.bot.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rexbot.bitrtix.bot.databinding.FragmentAccountBinding
import com.rexbot.bitrtix.bot.ui.common.BaseFragment

class AccountFragment : BaseFragment<FragmentAccountBinding>() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }



    override fun getViewBinding(): FragmentAccountBinding =
        FragmentAccountBinding.inflate(layoutInflater)
}
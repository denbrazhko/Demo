package com.rexbot.bitrtix.bot.ui.recovery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rexbot.bitrtix.bot.databinding.FragmentRecoveryPassword1Binding
import com.rexbot.bitrtix.bot.databinding.FragmentRecoveryPassword2Binding
import com.rexbot.bitrtix.bot.ui.common.BaseFragment

class RecoveryPasswordFragmentSecond :
    BaseFragment<FragmentRecoveryPassword2Binding, RecoveryPasswordViewModel>() {


    override fun init() {
        TODO("Not yet implemented")
    }

    override fun initObservers() {
        TODO("Not yet implemented")
    }

    override fun getViewModelClass(): Class<RecoveryPasswordViewModel> =
        RecoveryPasswordViewModel::class.java

    override fun getViewBinding(): FragmentRecoveryPassword2Binding =
        FragmentRecoveryPassword2Binding.inflate(layoutInflater)
}
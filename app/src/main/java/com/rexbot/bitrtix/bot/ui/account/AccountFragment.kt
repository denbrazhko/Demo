package com.rexbot.bitrtix.bot.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rexbot.bitrtix.bot.databinding.FragmentAccountBinding
import com.rexbot.bitrtix.bot.ui.common.BaseActivity
import com.rexbot.bitrtix.bot.ui.common.BaseFragment

class AccountFragment : BaseFragment<FragmentAccountBinding, AccountViewModel>() {

    override fun getViewModelClass(): Class<AccountViewModel> = AccountViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initObservers()
    }

    override fun init() {
        binding.cvUserIcon.setCardBackgroundColor(ResourcesUtils.getRandomColor())
        binding.tvUserIcon.text = viewModel.getUsernameFirstLetter()
        binding.tvUsername.text = viewModel.getUsername()
        binding.ivThemeSwitcher.setOnClickListener {
            (requireActivity() as BaseActivity<*>).changeTheme()
        }
    }

    override fun initObservers() {
    }


    override fun getViewBinding(): FragmentAccountBinding =
        FragmentAccountBinding.inflate(layoutInflater)
}
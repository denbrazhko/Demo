package com.rexbot.bitrtix.bot.ui.account

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rexbot.bitrtix.bot.databinding.FragmentAccountBinding
import com.rexbot.bitrtix.bot.ui.common.BaseActivity
import com.rexbot.bitrtix.bot.ui.common.BaseFragment
import com.rexbot.bitrtix.bot.ui.login.LoginActivity

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
        binding.btnExit.setOnClickListener { logout() }
    }

    private fun logout() {
        requireActivity().startActivity(Intent(requireContext(), LoginActivity::class.java))
        requireActivity().finish()
    }


    override fun getViewBinding(): FragmentAccountBinding =
        FragmentAccountBinding.inflate(layoutInflater)
}
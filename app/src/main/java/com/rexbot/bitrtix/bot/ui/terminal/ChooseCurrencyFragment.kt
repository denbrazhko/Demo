package com.rexbot.bitrtix.bot.ui.terminal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.rexbot.bitrtix.bot.databinding.FragmentAccountBinding
import com.rexbot.bitrtix.bot.databinding.FragmentChooseCurrencyBinding
import com.rexbot.bitrtix.bot.network.models.CurrencyModel
import com.rexbot.bitrtix.bot.ui.common.BaseFragment

class ChooseCurrencyFragment : BaseFragment<FragmentChooseCurrencyBinding, TerminalViewModel>() {

    private val adapter = CurrenciesAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        init()
    }

    override fun init() {
        viewModel.getCurrencies()
        adapter.onCurrencyClickListener = onCurrencyClickListener
        binding.rvCurrencies.adapter = adapter
    }

    override fun initObservers() {
        viewModel.currenciesLiveData.observe(viewLifecycleOwner, currenciesObserver)
    }

    private val currenciesObserver = Observer<List<CurrencyModel>> {
        if (it != null)
            adapter.currencies = it
    }

    private val onCurrencyClickListener =
        object : CurrenciesAdapter.OnCurrencyClickListener {
            override fun onClick(id: Int) {
            }
        }


    override fun getViewModelClass(): Class<TerminalViewModel> = TerminalViewModel::class.java

    override fun getViewBinding(): FragmentChooseCurrencyBinding =
        FragmentChooseCurrencyBinding.inflate(layoutInflater)
}

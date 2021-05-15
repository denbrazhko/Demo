package com.rexbot.bitrtix.bot.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rexbot.bitrtix.bot.R
import com.rexbot.bitrtix.bot.databinding.ActivityMainBinding
import com.rexbot.bitrtix.bot.ui.common.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {


    private val navController
        get() = Navigation.findNavController(this, R.id.navHost)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
        initObservers()
    }

    override fun init() {
    }

    override fun initObservers() {
        binding.bottomNav.setOnNavigationItemSelectedListener(menuNavigationListener)
    }

    private val menuNavigationListener =
        BottomNavigationView.OnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.terminal -> {
                    navController.navigate(R.id.terminalFragment)
                    true
                }
                R.id.account -> {
                    navController.navigate(R.id.accountFragment)
                    true
                }
                else -> false
            }

        }

    override fun getViewBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)
}
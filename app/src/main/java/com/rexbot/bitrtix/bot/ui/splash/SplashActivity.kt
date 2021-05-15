package com.rexbot.bitrtix.bot.ui.splash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.rexbot.bitrtix.bot.repositories.PrefsRepository
import com.rexbot.bitrtix.bot.ui.login.LoginActivity
import com.rexbot.bitrtix.bot.ui.splash.SplashActivityViewModel.Companion.TAG
import com.rexbot.bitrtix.bot.databinding.ActivitySplashBinding
import com.rexbot.bitrtix.bot.ui.common.BaseActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    private lateinit var viewModel: SplashActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate: ")
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        init()
        initObservers()
//        viewModel.startTimer()
    }

    override fun initObservers() {
        viewModel.timerLiveData.observe(this, {
            launchNextActivity()
        })
        binding.btnStart.setOnClickListener { launchNextActivity() }
    }

    private fun launchNextActivity() {
        // TODO: 09.05.2021 implement timer here. Change UserRepository constructor
        Log.i(TAG, "launchNextActivity: ${PrefsRepository(this).getCreds()}")
        val intent: Intent? =
//            if (PrefsRepository(this).getCreds() == null)
            Intent(this, LoginActivity::class.java)
//            else
//                null
        intent ?: return
        startActivity(intent)
        finish()
    }

    override fun init() {
        viewModel = ViewModelProvider.AndroidViewModelFactory(application)
            .create(SplashActivityViewModel::class.java)
    }

    override fun getViewBinding(): ActivitySplashBinding =
        ActivitySplashBinding.inflate(layoutInflater)
}

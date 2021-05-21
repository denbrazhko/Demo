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
import com.rexbot.bitrtix.bot.ui.common.Themes
import com.rexbot.bitrtix.bot.ui.main.MainActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    private lateinit var viewModel: SplashActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate: ")
        setDefaultTheme()
        init()
        if (!viewModel.isAuthNeed) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return
        }
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
        val intent = Intent(this, LoginActivity::class.java)
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

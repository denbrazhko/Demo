package com.rexbot.bitrtix.bot.ui.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<B : ViewBinding> : AppCompatActivity() {
    lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)

    }

    fun setNightMode(mode: Int) {
        if (AppCompatDelegate.getDefaultNightMode() == mode)
            return
        AppCompatDelegate.setDefaultNightMode(mode)
    }


    abstract fun getViewBinding(): B
    abstract fun init()
    abstract fun initObservers()

    private val TAG = "BASE_ACTIVITY_TAG"
}
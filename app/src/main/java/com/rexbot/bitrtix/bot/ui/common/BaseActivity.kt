package com.rexbot.bitrtix.bot.ui.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.rexbot.bitrtix.bot.ui.signup.SignupViewModel

abstract class BaseActivity<B : ViewBinding> : AppCompatActivity() {
    lateinit var binding: B

    lateinit var baseViewModel: BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        baseViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
            .create(BaseViewModel::class.java)
    }

    fun setCurrentTheme(theme: Themes) {
        baseViewModel.changeTheme(theme)
    }

    fun setDefaultTheme() {
        baseViewModel.setDefaultTheme()
    }

    fun changeTheme() {
        if (baseViewModel.themeLiveData.value == Themes.NIGHT)
            setCurrentTheme(Themes.LIGHT)
        else setCurrentTheme(Themes.NIGHT)
    }


    abstract fun getViewBinding(): B
    abstract fun init()
    abstract fun initObservers()

    private val TAG = "BASE_ACTIVITY_TAG"
}
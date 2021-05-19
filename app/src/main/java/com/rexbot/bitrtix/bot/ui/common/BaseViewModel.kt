package com.rexbot.bitrtix.bot.ui.common

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.rexbot.bitrtix.bot.repositories.PrefsRepository

class BaseViewModel(application: Application) : AndroidViewModel(application) {

    private val prefsRepository = PrefsRepository(getApplication())

    val themeLiveData: MutableLiveData<Themes> = MutableLiveData(Themes.NIGHT)

    fun changeTheme(theme: Themes) {
        val mode =
            if (theme == Themes.NIGHT)
                AppCompatDelegate.MODE_NIGHT_YES
            else AppCompatDelegate.MODE_NIGHT_NO
        if (mode == AppCompatDelegate.getDefaultNightMode())
            return
        AppCompatDelegate.setDefaultNightMode(mode)
        prefsRepository.saveTheme(theme)
        themeLiveData.value = theme
    }

    fun setDefaultTheme() {
        changeTheme(prefsRepository.getTheme())
    }
}
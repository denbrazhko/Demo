package com.rexbot.bitrtix.bot.repositories

import android.content.Context
import android.content.SharedPreferences
import com.rexbot.bitrtix.bot.ui.common.Themes

class PrefsRepository(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveCreds(username: String) {
        prefs.edit().putString(USER_PREFS, username).apply()
    }

    fun getCreds() = prefs.getString(USER_PREFS, null)

    fun getTheme(): Themes =
        Themes.getThemeFromString(prefs.getString(THEME_PREFS, null))

    fun saveTheme(theme: Themes) {
        prefs.edit().putString(THEME_PREFS, theme.toString()).apply()
    }

    companion object {
        private const val PREFS_NAME = "BITRIX_BOT_PREFS"
        private const val USER_PREFS = "USERNAME_P"
        private const val THEME_PREFS = "THEME_P"

    }

}
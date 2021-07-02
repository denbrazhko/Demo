package com.rexbot.bitrtix.bot.repositories

import android.content.Context
import android.content.SharedPreferences
import com.rexbot.bitrtix.bot.ui.common.Themes

class PrefsRepository(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveCreds(username: String, token: String = "") {
        prefs.edit().putString(USER_PREFS, username).apply()
        prefs.edit().putString(BEARER_PREFS, token).apply()
    }

    fun getCreds() = prefs.getString(USER_PREFS, null)

    fun getBearer(): String = prefs.getString(BEARER_PREFS, "") ?: ""

    fun getTheme(): Themes =
        Themes.getThemeFromString(prefs.getString(THEME_PREFS, null))

    fun saveTheme(theme: Themes) {
        prefs.edit().putString(THEME_PREFS, theme.toString()).apply()
    }

    fun getLastAuthTime() = prefs.getLong(LAST_AUTH_TIME, System.currentTimeMillis())

    fun saveLastAuthTime() {
        prefs.edit().putLong(LAST_AUTH_TIME, System.currentTimeMillis()).apply()
    }

    companion object {
        private const val PREFS_NAME = "BITRIX_BOT_PREFS"
        private const val USER_PREFS = "USERNAME_P"
        private const val THEME_PREFS = "THEME_P"
        private const val LAST_AUTH_TIME = "LAST_AUTH_TIME"
        private const val BEARER_PREFS = "BEARER_PREFS"
    }

}
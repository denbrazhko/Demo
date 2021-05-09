package com.rexbot.bitrtix.bot.repositories

import android.content.Context
import android.content.SharedPreferences

class PrefsRepository(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveCreds(username: String) {
        prefs.edit().putString(USER_PREFS, username).apply()
    }

    fun getCreds() = prefs.getString(USER_PREFS, null)

    companion object {
        private const val PREFS_NAME = "BITRIX_BOT_PREFS"
        private const val USER_PREFS = "USERNAME_P"

    }
}
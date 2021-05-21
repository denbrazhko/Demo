package com.rexbot.bitrtix.bot.repositories

import com.rexbot.bitrtix.bot.App
import com.rexbot.bitrtix.bot.network.helpers.UserApiHelper
import com.rexbot.bitrtix.bot.network.models.SignInResponseModel
import com.rexbot.bitrtix.bot.network.models.SignupResponseModel
import java.sql.Time
import java.util.*
import java.util.concurrent.TimeUnit

class UserRepository(
    private val apiHelper: UserApiHelper,
    private val prefsRepository: PrefsRepository = PrefsRepository(App.instance)
) {
    suspend fun signIn(user: String, pass: String): SignInResponseModel =
        apiHelper.signIn(user, pass)

    suspend fun signUp(user: String, pass: String): SignupResponseModel =
        apiHelper.signUp(user, pass)

    fun getUsernameFirstLetter(): String {
        val username = prefsRepository.getCreds() ?: ""
        return if (username.length >= 0)
            username[0].toString()
        else "A"
    }

    fun logout() {
        prefsRepository.saveCreds("")
    }

    fun checkReAuthNeed(): Boolean {
        val last = prefsRepository.getLastAuthTime()
        val current = System.currentTimeMillis()
        return current - last >= TIME_TO_RE_AUTH
    }

    fun getUsername() = prefsRepository.getCreds()

    companion object {
        private val TIME_TO_RE_AUTH = TimeUnit.DAYS.toMillis(7)
    }
}
package com.rexbot.bitrtix.bot.repositories

import com.rexbot.bitrtix.bot.App
import com.rexbot.bitrtix.bot.network.helpers.UserApiHelper
import com.rexbot.bitrtix.bot.network.models.SignInResponseModel
import com.rexbot.bitrtix.bot.network.models.SignupResponseModel

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

    fun getUsername() = prefsRepository.getCreds()
}
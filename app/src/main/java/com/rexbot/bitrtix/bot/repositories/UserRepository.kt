package com.rexbot.bitrtix.bot.repositories

import com.rexbot.bitrtix.bot.network.helpers.UserApiHelper
import com.rexbot.bitrixbot.bot.network.models.SignInResponseModel
import com.rexbot.bitrixbot.bot.network.models.SignupResponseModel

class UserRepository(private val apiHelper: UserApiHelper) {
    suspend fun signIn(user: String, pass: String): SignInResponseModel = apiHelper.signIn(user, pass)
    suspend fun signUp(user: String, pass: String): SignupResponseModel = apiHelper.signUp(user, pass)
}
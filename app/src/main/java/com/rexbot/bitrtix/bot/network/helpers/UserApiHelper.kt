package com.rexbot.bitrtix.bot.network.helpers

import com.rexbot.bitrixbot.bot.network.models.SignInResponseModel
import com.rexbot.bitrixbot.bot.network.models.SignupResponseModel
import com.rexbot.bitrtix.bot.utils.PassCrypt
import com.rexbot.bitrtix.bot.network.api.UserApi

class UserApiHelper(private val userApi: UserApi) {

    suspend fun signIn(
            userName: String,
            password: String
    ): SignInResponseModel {
        val encodedPass = PassCrypt.encrypt(password, PassCrypt.KEY)
        return userApi.signIn(userName, encodedPass)
    }

    suspend fun signUp(
            userName: String,
            password: String
    ): SignupResponseModel {
        val encodedPass = PassCrypt.encrypt(password, PassCrypt.KEY)
        return userApi.signUp(userName, encodedPass)
    }
}
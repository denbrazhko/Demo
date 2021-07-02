package com.rexbot.bitrtix.bot.network.helpers

import com.rexbot.bitrtix.bot.network.models.SignInResponseModel
import com.rexbot.bitrtix.bot.network.models.SignupResponseModel
import com.rexbot.bitrtix.bot.network.api.UserApi
import com.rexbot.bitrtix.bot.utils.CryptAES

class UserApiHelper(private val userApi: UserApi) {

    suspend fun signIn(
        userName: String,
        password: String,
        captcha: String
    ): SignInResponseModel {

        val encodedPass = CryptAES().encrypt(password)
        return userApi.signIn(email = userName, password = encodedPass, captcha = captcha)
    }

    suspend fun signUp(
        userName: String,
        password: String,
        captcha: String
    ): SignupResponseModel {
        val encodedPass = CryptAES().encrypt(password)
        return userApi.signUp(email = userName, password = encodedPass, captcha = captcha)
    }
}
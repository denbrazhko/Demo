package com.rexbot.bitrtix.bot.network.api

import com.rexbot.bitrixbot.bot.network.models.SignInResponseModel
import com.rexbot.bitrixbot.bot.network.models.SignupResponseModel
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {

    @GET("reg.php")
    suspend fun signUp(
        @Query("email") email: String,
        @Query("pass") password: String
    ):SignupResponseModel

    @GET("login.php")
    suspend fun signIn(
        @Query("email") email: String,
        @Query("pass") password: String
    ):SignInResponseModel
}
package com.rexbot.bitrtix.bot.network.api

import com.rexbot.bitrtix.bot.network.models.SignInResponseModel
import com.rexbot.bitrtix.bot.network.models.SignupResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {

    @GET("reg.php")
    suspend fun signUp(
        @Query("email") email: String,
        @Query("pass") password: String
    ): SignupResponseModel

    @GET("login.php")
    suspend fun signIn(
        @Query("email") email: String,
        @Query("pass") password: String
    ): SignInResponseModel
}
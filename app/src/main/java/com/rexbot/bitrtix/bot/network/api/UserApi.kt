package com.rexbot.bitrtix.bot.network.api

import com.rexbot.bitrtix.bot.network.models.SignInResponseModel
import com.rexbot.bitrtix.bot.network.models.SignupResponseModel
import retrofit2.http.*
import java.lang.invoke.CallSite

interface UserApi {

    @POST("user/auth")
    @FormUrlEncoded
    suspend fun signIn(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("captcha") captcha: String
    ): SignInResponseModel

    @PUT("user/newuser")
    @FormUrlEncoded
    suspend fun signUp(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("site") site: String = "botrex",
        @Field("captcha") captcha: String
    ): SignupResponseModel


}
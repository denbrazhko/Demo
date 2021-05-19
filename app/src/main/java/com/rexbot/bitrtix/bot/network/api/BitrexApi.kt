package com.rexbot.bitrtix.bot.network.api

import com.rexbot.bitrtix.bot.network.models.CurrenciesResponseModel
import retrofit2.http.GET

interface BitrexApi {

    @GET("public/getcurrencies")
    suspend fun getCurrencies(): CurrenciesResponseModel
}
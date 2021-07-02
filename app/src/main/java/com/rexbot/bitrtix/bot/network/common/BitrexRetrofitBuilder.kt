package com.rexbot.bitrtix.bot.network.common

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.rexbot.bitrtix.bot.App
import com.rexbot.bitrtix.bot.BuildConfig
import com.rexbot.bitrtix.bot.network.api.BitrexApi
import com.rexbot.bitrtix.bot.network.api.UserApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object BitrexRetrofitBuilder {

    private const val BASE_URL = BuildConfig.BASE_BITREX_URL
    private const val TIMEOUT = 15L

    private fun getRetrofitClient(): Retrofit {
        val client = OkHttpClient.Builder()
            .addInterceptor(ChuckerInterceptor(context = App.instance))
            .addInterceptor(AuthenticationInterceptor())
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val userApi: BitrexApi = getRetrofitClient().create(BitrexApi::class.java)
}
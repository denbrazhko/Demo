package com.rexbot.bitrtix.bot.network.common

import com.rexbot.bitrtix.bot.BuildConfig
import com.rexbot.bitrtix.bot.network.api.UserApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitBuilder {

    private const val BASE_URL = BuildConfig.BASE_API_URL

    private fun getRetrofitClient(): Retrofit {
//        val client = OkHttpClient.Builder()
//            .addInterceptor(ChuckInterceptor(App.instance))
//            .build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
//            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val userApi: UserApi = getRetrofitClient().create(UserApi::class.java)
}
package com.rexbot.bitrtix.bot.network.common

import com.rexbot.bitrtix.bot.repositories.UserRepository
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthenticationInterceptor : Interceptor {

    private val userRepository = UserRepository()

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val builder = original.newBuilder()
        val token = userRepository.getBearerToken()

        if (token.isNotEmpty())
            builder.addHeader("Authorization", "Bearer $token")

        val request = builder.build()
        return chain.proceed(request)
    }
}
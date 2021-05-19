package com.rexbot.bitrtix.bot.network.helpers

import com.rexbot.bitrtix.bot.network.api.BitrexApi

class BitrexApiHelper(private val bitrexApi: BitrexApi) {

    suspend fun getCurrencies() = bitrexApi.getCurrencies()
}
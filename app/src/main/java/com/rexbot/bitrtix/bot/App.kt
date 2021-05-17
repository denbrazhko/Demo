package com.rexbot.bitrtix.bot

import android.app.Application
import android.util.Log
import com.rexbot.bitrtix.bot.utils.CryptAES
import com.rexbot.bitrtix.bot.utils.PassCrypt

class App : Application() {

    private val TAG = APP_TAG

    override fun onCreate() {
        super.onCreate()
        instance = this
    }


    companion object {
        lateinit var instance: App
            private set
        private const val APP_TAG = "BOTREX_APP_TAG"
    }
}
package com.rexbot.bitrtix.bot.repositories

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.google.gson.Gson
import com.rexbot.bitrtix.bot.entities.ApiKeyModel

class ApiKeysRepository(context: Context) {

    val prefs = context.getSharedPreferences(API_KEYS_KEY, MODE_PRIVATE)
    private val gson = Gson()

    @SuppressLint("ApplySharedPref")
    fun saveKey(key: ApiKeyModel) {
        var keysString: MutableSet<String> = getSetOfKeys()

        val keys: MutableList<ApiKeyModel> = mutableListOf()
        keys.apply {
            keysString.map {
                add(gson.fromJson(it, ApiKeyModel::class.java))
            }
        }
        if (keys.contains(key))
            return
        keys.add(key)
        keysString = mutableSetOf()
        keysString.apply {
            keys.map {
                add(gson.toJson(it))
            }
        }
        prefs.edit().putStringSet(API_KEYS_KEY, keysString).commit()
    }

    fun getSetOfKeys(): MutableSet<String> =
        prefs.getStringSet(API_KEYS_KEY, mutableSetOf()) ?: mutableSetOf()

    fun getMutableListOfKeys(): MutableList<ApiKeyModel> {
        val list = mutableListOf<ApiKeyModel>()
        return list.apply {
            getSetOfKeys().map {
                add(gson.fromJson(it, ApiKeyModel::class.java))
            }
        }
    }


    companion object {
        private const val PREFS_NAME = "API_KEYS_PREFS"
        private const val API_KEYS_KEY = "API_KEYS"
    }
}
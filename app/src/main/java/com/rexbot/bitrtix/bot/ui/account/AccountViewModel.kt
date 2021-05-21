package com.rexbot.bitrtix.bot.ui.account

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.rexbot.bitrtix.bot.entities.ApiKeyModel
import com.rexbot.bitrtix.bot.network.common.RetrofitBuilder
import com.rexbot.bitrtix.bot.network.helpers.UserApiHelper
import com.rexbot.bitrtix.bot.repositories.ApiKeysRepository
import com.rexbot.bitrtix.bot.repositories.PrefsRepository
import com.rexbot.bitrtix.bot.repositories.UserRepository

class AccountViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository = UserRepository(UserApiHelper(RetrofitBuilder.userApi))
    private val apiKeysRepository = ApiKeysRepository(getApplication())

    val keysLiveData: MutableLiveData<MutableList<ApiKeyModel>> = MutableLiveData()


    fun getUsernameFirstLetter() = userRepository.getUsernameFirstLetter()
    fun getUsername() = userRepository.getUsername()

    fun saveApiKey(
        name: String,
        public: String,
        secret: String
    ) {
        val key = ApiKeyModel(name, public, secret)
        apiKeysRepository.saveKey(key)
        keysLiveData.value = getKeysList()
    }

    fun logout(){
        userRepository.logout()
    }

    fun getKeysList() = apiKeysRepository.getMutableListOfKeys()


}
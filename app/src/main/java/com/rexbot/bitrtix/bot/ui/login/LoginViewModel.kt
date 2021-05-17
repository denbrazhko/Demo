package com.rexbot.bitrtix.bot.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.rexbot.bitrtix.bot.App
import com.rexbot.bitrtix.bot.R
import com.rexbot.bitrtix.bot.repositories.PrefsRepository
import com.rexbot.bitrtix.bot.repositories.UserRepository
import com.rexbot.bitrtix.bot.network.helpers.UserApiHelper
import com.rexbot.bitrtix.bot.network.models.SignInResponseModel
import com.rexbot.bitrtix.bot.network.Resource
import com.rexbot.bitrtix.bot.network.common.RetrofitBuilder
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository = UserRepository(UserApiHelper(RetrofitBuilder.userApi))
    private val prefsRepository = PrefsRepository(getApplication())


    val signInLiveData: MutableLiveData<Resource<SignInResponseModel>> = MutableLiveData()


    fun signIn(
        username: String,
        pass: String
    ) {
        liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            var data: SignInResponseModel? = null
            try {
                data = userRepository.signIn(username, pass)
                if (data.result == "error")
                    throw Exception(data.error)
                if (!data.activated)
                    throw Exception(App.instance.getString(R.string.email_not_verified))
                saveCreds(username)
                emit(Resource.success(data))
            } catch (e: Exception) {
                val error =
                    if (data != null && data.error.isNotEmpty())
                        data.error
                    else e.message
                emit(Resource.error(data, error))
            }
        }.observeForever { signInLiveData.postValue(it) }
    }

    fun getCreds() = prefsRepository.getCreds()

    private fun saveCreds(username: String) {
        prefsRepository.saveCreds(username)
    }

}
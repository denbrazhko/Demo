package com.rexbot.bitrtix.bot.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.rexbot.bitrtix.bot.App
import com.rexbot.bitrtix.bot.R
import com.rexbot.bitrtix.bot.repositories.PrefsRepository
import com.rexbot.bitrtix.bot.repositories.UserRepository
import com.rexbot.bitrtix.bot.network.helpers.UserApiHelper
import com.rexbot.bitrtix.bot.network.models.SignInResponseModel
import com.rexbot.bitrtix.bot.network.Resource
import com.rexbot.bitrtix.bot.network.common.RetrofitBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository = UserRepository(UserApiHelper(RetrofitBuilder.userApi))


    val signInLiveData: MutableLiveData<Resource<SignInResponseModel>> = MutableLiveData()


    fun signIn(
        username: String,
        pass: String,
        captcha: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            signInLiveData.postValue(Resource.loading(null))
            var data: SignInResponseModel? = null
            try {
                data = userRepository.signIn(username, pass, captcha)
                if (data.error.isNotEmpty())
                    throw Exception(data.error)
                if (!data.isVerified)
                    throw Exception(App.instance.getString(R.string.email_not_verified))
                saveCreds(username, data.bearer)
                signInLiveData.postValue(Resource.success(data))
            } catch (e: Exception) {
                val error =
                    if (data != null && data.error.isNotEmpty())
                        data.error
                    else e.message
                signInLiveData.postValue(Resource.error(data, error))
            }
        }
    }

    fun getCreds() = userRepository.getUsername()

    private fun saveCreds(username: String, token: String) {
        userRepository.successLogin(username, token)
    }

}
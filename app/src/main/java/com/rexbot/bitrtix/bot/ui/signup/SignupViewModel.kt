package com.rexbot.bitrtix.bot.ui.signup

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.rexbot.bitrtix.bot.App
import com.rexbot.bitrtix.bot.R
import com.rexbot.bitrtix.bot.repositories.PrefsRepository
import com.rexbot.bitrtix.bot.repositories.UserRepository
import com.rexbot.bitrtix.bot.network.helpers.UserApiHelper
import com.rexbot.bitrtix.bot.network.models.SignupResponseModel
import com.rexbot.bitrtix.bot.network.Resource
import com.rexbot.bitrtix.bot.network.common.RetrofitBuilder
import com.rexbot.bitrtix.bot.network.models.SignInResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class SignupViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository = UserRepository(UserApiHelper(RetrofitBuilder.userApi))
    private val prefsRepository = PrefsRepository(getApplication())


    val signUpLiveData: MutableLiveData<Resource<SignupResponseModel>> = MutableLiveData()


    fun signUp(
        username: String,
        pass: String,
        captcha: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            signUpLiveData.postValue(Resource.loading(null))
            var data: SignupResponseModel? = null
            try {
                data = userRepository.signUp(username, pass, captcha)
                if (data.error.isNotEmpty())
                    throw Exception(data.error)

                saveCreds(username)
                signUpLiveData.postValue(Resource.success(data))
            } catch (e: Exception) {
                val error =
                    if (data != null && data.error.isNotEmpty())
                        data.error
                    else e.message
                signUpLiveData.postValue(Resource.error(data, error))
            }
        }
    }


    private fun saveCreds(username: String) {
        prefsRepository.saveCreds(username)
    }

    companion object {
        const val TAG = "SIGN_UP_TAG"
    }

}
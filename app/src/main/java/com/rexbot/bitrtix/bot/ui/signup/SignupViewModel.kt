package com.rexbot.bitrtix.bot.ui.signup

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.rexbot.bitrtix.bot.repositories.PrefsRepository
import com.rexbot.bitrtix.bot.repositories.UserRepository
import com.rexbot.bitrtix.bot.network.helpers.UserApiHelper
import com.rexbot.bitrtix.bot.network.models.SignupResponseModel
import com.rexbot.bitrtix.bot.network.Resource
import com.rexbot.bitrtix.bot.network.common.RetrofitBuilder
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class SignupViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository = UserRepository(UserApiHelper(RetrofitBuilder.userApi))
    private val prefsRepository = PrefsRepository(getApplication())


    val signUpLiveData: MutableLiveData<Resource<SignupResponseModel>> = MutableLiveData()


    fun signUp(
        username: String,
        pass: String
    ) {
        liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            var data: SignupResponseModel? = null
            try {
                data = userRepository.signUp(username, pass)
                if (data.result == "error") {
                    throw Exception(data.error)
                }
                saveCreds(username)
                emit(Resource.success(data))
            } catch (e: Exception) {
                Log.e(TAG, "signUp: ", e)
                val error =
                    if (data != null && data.error.isNotEmpty())
                        data.error
                    else e.message
                emit(Resource.error(data, error))
            }
        }.observeForever { signUpLiveData.postValue(it) }
    }


    private fun saveCreds(username: String) {
        prefsRepository.saveCreds(username)
    }

    companion object {
        const val TAG = "SIGN_UP_TAG"
    }

}
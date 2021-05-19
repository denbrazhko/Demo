package com.rexbot.bitrtix.bot.ui.terminal

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.rexbot.bitrtix.bot.network.Resource
import com.rexbot.bitrtix.bot.network.common.BitrexRetrofitBuilder
import com.rexbot.bitrtix.bot.network.helpers.BitrexApiHelper
import com.rexbot.bitrtix.bot.network.models.CurrenciesResponseModel
import com.rexbot.bitrtix.bot.network.models.CurrencyModel
import com.rexbot.bitrtix.bot.network.models.SignupResponseModel
import com.rexbot.bitrtix.bot.ui.signup.SignupViewModel
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class TerminalViewModel(application: Application):AndroidViewModel(application) {

    private val bitrexApiHelper = BitrexApiHelper(BitrexRetrofitBuilder.userApi)

    val currenciesLiveData: MutableLiveData<List<CurrencyModel>> = MutableLiveData()


    fun getCurrencies() {
        liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            var data: CurrenciesResponseModel? = null
            try {
                data = bitrexApiHelper.getCurrencies()
                if (!data.success) {
                    throw Exception(data.message)
                }
                emit(Resource.success(data))
            } catch (e: Exception) {
                Log.e(SignupViewModel.TAG, "getCurrencies: ", e)
                val error =
                    if (data != null && data.message.isNotEmpty())
                        data.message
                    else e.message
                emit(Resource.error(data, error))
            }
        }.observeForever { currenciesLiveData.postValue(it.data?.currencies) }
    }
}
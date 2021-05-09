package com.rexbot.bitrtix.bot.ui.splash

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivityViewModel(application: Application) : AndroidViewModel(application) {


    val timerLiveData: MutableLiveData<Unit> = MutableLiveData()


    fun startTimer() {
        Log.i(TAG, "startTimer: ")
        GlobalScope.launch(Dispatchers.Main) {
            delay(1000)
            Log.i(TAG, "startTimer: end")
            timerLiveData.postValue(Unit)
        }
    }

    companion object {
        const val TAG = "SPLASH_TAG"
    }
}
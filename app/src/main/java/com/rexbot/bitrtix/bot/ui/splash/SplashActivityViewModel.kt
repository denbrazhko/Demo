package com.rexbot.bitrtix.bot.ui.splash

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.rexbot.bitrtix.bot.entities.User
import com.rexbot.bitrtix.bot.repositories.PrefsRepository
import com.rexbot.bitrtix.bot.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivityViewModel(application: Application) : AndroidViewModel(application) {


    val timerLiveData: MutableLiveData<Unit> = MutableLiveData()
    private val userRepository = UserRepository()


    fun startTimer() {
        Log.i(TAG, "startTimer: ")
        GlobalScope.launch(Dispatchers.Main) {
            delay(1000)
            Log.i(TAG, "startTimer: end")
            timerLiveData.postValue(Unit)
        }
    }

    val  isAuthNeed get() = userRepository.checkReAuthNeed()

    companion object {
        const val TAG = "SPLASH_TAG"
    }
}
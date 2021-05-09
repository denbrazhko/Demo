package com.rexbot.bitrixbot.bot.network.models

import com.google.gson.annotations.SerializedName

data class SignupResponseModel(
        @SerializedName("result")
        val result: String,
        @SerializedName("error")
        val error: String,
        @SerializedName("data")
        val data: String
)

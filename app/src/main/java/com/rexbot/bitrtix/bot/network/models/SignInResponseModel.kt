package com.rexbot.bitrtix.bot.network.models

import com.google.gson.annotations.SerializedName

data class SignInResponseModel(
        @SerializedName("result")
        val result: String,
        @SerializedName("error")
        val error: String,
        @SerializedName("data")
        val data: String
)

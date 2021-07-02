package com.rexbot.bitrtix.bot.network.models

import com.google.gson.annotations.SerializedName

data class SignupResponseModel(
    @SerializedName("UID")
    val UID: String,
    @SerializedName("error")
    val error: String,
    @SerializedName("id")
    val id: Long
)

package com.rexbot.bitrtix.bot.network.models

import com.google.gson.annotations.SerializedName

data class SignInResponseModel(
    @SerializedName("error")
    val error: String,
    @SerializedName("bearer")
    val bearer: String,
    @SerializedName("verification")
    val isVerified: Boolean
)

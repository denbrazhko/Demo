package com.rexbot.bitrtix.bot.network.models

import com.google.gson.annotations.SerializedName

data class CurrenciesResponseModel(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("result")
    val currencies: List<CurrencyModel>
)

data class CurrencyModel(
    @SerializedName("Currency")
    val shortName: String,
    @SerializedName("CurrencyLong")
    val longName: String,
    @SerializedName("MinConfirmation")
    val minConfirmation: Int,
    @SerializedName("TxFee")
    val fee: Double,
    @SerializedName("IsActive")
    val isActive: Boolean,
    @SerializedName("IsRestricted")
    val isRestricted: Boolean,
    @SerializedName("CoinType")
    val coinType: String,
    @SerializedName("BaseAddress")
    val baseAddress: String,
    @SerializedName("Notice")
    val notice: String
)


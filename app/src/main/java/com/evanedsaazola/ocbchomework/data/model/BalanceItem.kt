package com.evanedsaazola.ocbchomework.data.model

import com.google.gson.annotations.SerializedName

data class BalanceItem(
    @SerializedName("status")
    val status: String?,
    @SerializedName("accountNo")
    val accountNo: String?,
    @SerializedName("balance")
    val balance: Double?
)
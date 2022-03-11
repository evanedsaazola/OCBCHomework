package com.evanedsaazola.ocbchomework.data.model

import com.evanedsaazola.ocbchomework.domain.Balance
import com.google.gson.annotations.SerializedName

data class BalanceItem(
    @SerializedName("status")
    val status: String?,
    @SerializedName("accountNo")
    val accountNo: String?,
    @SerializedName("balance")
    val balance: Int?
) {
    fun toBalance(): Balance {
        return Balance(
            status = status.orEmpty(),
            accountNo = accountNo.orEmpty(),
            balance = balance ?: 0
        )
    }
}
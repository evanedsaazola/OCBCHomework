package com.evanedsaazola.ocbchomework.data.model

import com.google.gson.annotations.SerializedName

data class LoginItem(
    @SerializedName("status")
    val status: String?,
    @SerializedName("token")
    val accountToken: String?,
    @SerializedName("error")
    val errorMessage: String?,
    @SerializedName("username")
    val username: String?,
    @SerializedName("accountNo")
    val accountNo: String?
)
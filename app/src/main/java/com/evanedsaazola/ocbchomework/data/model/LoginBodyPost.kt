package com.evanedsaazola.ocbchomework.data.model

import com.google.gson.annotations.SerializedName

data class LoginBodyPost(
    @SerializedName("username") val username: String,
    @SerializedName("password") val password: String
)

package com.evanedsaazola.ocbchomework.data

import com.evanedsaazola.ocbchomework.data.model.BalanceItem
import com.evanedsaazola.ocbchomework.data.model.LoginBodyPost
import com.evanedsaazola.ocbchomework.data.model.LoginItem
import com.evanedsaazola.ocbchomework.data.model.TransactionsItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiServices {

    @POST("/login")
    fun postLogin(@Body requestBody: LoginBodyPost): Call<LoginItem>

    @GET("/balance")
    fun getBalance(): Call<BalanceItem>

    @GET("/transactions")
    fun getTransactions(): Call<TransactionsItem>

}
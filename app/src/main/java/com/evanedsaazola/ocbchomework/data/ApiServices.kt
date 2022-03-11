package com.evanedsaazola.ocbchomework.data

import com.evanedsaazola.ocbchomework.data.model.BalanceItem
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiServices {

    @POST("/login")
    suspend fun postLogin(@Body requestBody: RequestBody): Response<ResponseBody>

    @GET("/balance")
    suspend fun getBalance(): Response<BalanceItem>

}
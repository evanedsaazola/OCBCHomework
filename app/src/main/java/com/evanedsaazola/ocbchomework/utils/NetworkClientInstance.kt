package com.evanedsaazola.ocbchomework.utils

import android.content.Context
import com.evanedsaazola.ocbchomework.data.ApiServices
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkClientInstance(context: Context) {

    private lateinit var apiServices: ApiServices
    private val sessionManager = SessionManager(context)

    fun getApiServices(): ApiServices {
        if (!this::apiServices.isInitialized) {
            val headerInterceptor = Interceptor { chain ->
                val requestBuilder = chain.request().newBuilder()
                sessionManager.getJwtToken()?.let {
                    requestBuilder.header("Authorization", it)
                }
                chain.proceed(requestBuilder.build())
            }

            val httpClient = OkHttpClient().newBuilder()
                .followRedirects(true)
                .addInterceptor(headerInterceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build()

            apiServices = retrofit.create(ApiServices::class.java)
        }
        return apiServices
    }

}
package com.evanedsaazola.ocbchomework.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkConfig {

    companion object {
        val BASE_URL = "https://green-thumb-64168.uc.r.appspot.com/"

        fun getNetworkConfig(): Retrofit {
            return Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

    }

}
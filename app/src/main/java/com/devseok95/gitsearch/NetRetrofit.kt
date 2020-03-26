package com.devseok95.gitsearch

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetRetrofit private constructor() {
    var retrofit = Retrofit.Builder().baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    var service = retrofit.create(RetrofitService::class.java)

    companion object {
        val instance = NetRetrofit()
    }
}
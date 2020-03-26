package com.devseok95.gitsearch

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.*

interface RetrofitService {
    @GET("users/{user}/repos")
    fun getListRepos(@Path("user") id: String?): Call<ArrayList<JsonObject?>?>?
}
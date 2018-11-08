package com.dsm2018.get_terra_android_v2.Connector

import com.dsm2018.get_terra_android_v2.RetrofitGetQuestion
import com.google.gson.JsonObject

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Url

interface API {
    @GET("/solve/{boothName}")
    @Headers("Authorization")
    fun getQuestion(
            @Path("boothName") boothName: String): Call<List<RetrofitGetQuestion>>

    @POST("/solve/{boothName}")
    @Headers("Authorization", "Content-Type: application/json")
    fun postQuestion(
            @Path("boothName") boothName: String,
            @Body jsonObject: JsonObject
    ): Call<Void>
}

package com.dsm2018.get_terra_android_v2.Connector;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API {
    @GET("/auth/{gameKey}")
    Call<Void> key_certified(@Body JsonObject jsonObject);

    @POST("/auth/{gameKey}")
    Call<Void> login(@Body JsonObject jsonObject);



}

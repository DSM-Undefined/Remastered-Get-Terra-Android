package com.dsm2018.get_terra_android_v2.Connector;

import android.content.Intent;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface API {
    @GET("/auth/{gameKey}")
    Call<JsonObject> key_certified(@Header("gameKey") Integer jsonObject);

    @POST("/auth/{gameKey}")
    Call<JsonObject> login(
            @Path("gameKey") String gameKey,
            @Body JsonObject jsonObject);

    @GET("/team")
    Call<JsonObject> teamList(@Header("Authorization") String jsonObject);

    @POST("/team")
    Call<Void> team_choice(@Body JsonObject jsonObject);

}
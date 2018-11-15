package com.dsm2018.get_terra_android_v2.Connector;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface API {
    @GET("/solve/{boothName}")
    Call<solveGet> getQuestion(
            @Header("Authorization") String authorization,
            @Path("boothName") String boothName);

    @POST("/solve/{boothName}")
    @Headers("Content-Type: application/json")
    Call<Void> postQuestion(
            @Header("Authorization") String authorization,
            @Path("boothName") String boothName,
            @Body JsonObject jsonObject
    );
}

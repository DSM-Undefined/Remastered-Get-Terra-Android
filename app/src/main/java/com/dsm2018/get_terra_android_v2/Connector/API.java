package com.dsm2018.get_terra_android_v2.Connector;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface API {
    @GET("/solve/{boothName}")
    @Headers("Authorization")
    Call<Void>getQuestion(
            @Path("boothName") String boothName,
            @Body JsonObject jsonObject);
    @POST("/solve/{boothName}")
    @Headers({"Authorization","Content-Type: application/json" })
    Call<Void>postQuestion(
        @Path("boothName")String boothName,
        @Body JsonObject jsonObject
    );
}

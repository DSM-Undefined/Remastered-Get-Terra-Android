package com.dsm2018.get_terra_android_v2.Connector;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

public interface API {
    @GET("session")
    Call<Void> post_login(@Body JsonObject jsonObject);
}

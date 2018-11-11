package com.dsm2018.get_terra_android_v2.Connector;

import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface API {
    @GET("map")
    Call<JsonArray> getMap(
            @Header("") String Authorization
    );
}

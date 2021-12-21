package com.example.myapplication.service;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface InfoService {
    @POST("/pn/async/auth/")
    @Headers({
            "Content-Type: application/json;charset=UTF-8"
    })
    Call<JsonObject> getString(@Query("email") String email,
                               @Query("password") String password);
}

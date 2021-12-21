package com.example.myapplication.common;

import androidx.annotation.Nullable;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitCall {
    @POST("/pn/async/auth/")
//    @Headers("content-type:json") //添加头信息固态，放入参数中则变成动态添加
    Call<ResponseBody>
    Info(@Query("auth_result") String auth_result
            , @Query("pay_status") String pay_status
            , @Nullable @Query("contract_course") String contract_course
            , @Nullable @Query("lcus_no") String lcus_no);
}
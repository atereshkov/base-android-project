package com.github.handioq.baseandroid.logic;

import com.github.handioq.baseandroid.model.dto.FaqResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("/api/test")
    Call<FaqResponse> getFaq();

    @GET("/api/test2")
    Call<FaqResponse> getFaq2();
}

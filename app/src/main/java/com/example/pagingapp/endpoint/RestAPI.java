package com.example.pagingapp.endpoint;

import com.example.pagingapp.model.Example;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestAPI {
    @GET("answers")
    Call<Example> getAnswers(@Query("page") int page, @Query("pagesize") int pagesize, @Query("site") String site);
}

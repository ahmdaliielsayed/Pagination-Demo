package com.example.pagingapp.util;

import com.example.pagingapp.endpoint.RestAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static String BASE_URL = "https://api.stackexchange.com/2.2/";
    private static Retrofit retrofit;

    private static Retrofit getRetrofitInstance(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

    public static RestAPI getRestAPI() {
        return getRetrofitInstance().create(RestAPI.class);
    }
}

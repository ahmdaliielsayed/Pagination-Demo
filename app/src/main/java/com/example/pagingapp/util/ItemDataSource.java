package com.example.pagingapp.util;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.pagingapp.model.Data;
import com.example.pagingapp.model.Example;
import com.example.pagingapp.model.Item;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// <Integer, Item>
// Integer ==> represents page number ex: page1, page2, ...
// Item ==> represents list on each page
public class ItemDataSource extends PageKeyedDataSource<Integer, Item> {

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Item> callback) {
        RetrofitClient.getRestAPI().getAnswers(Data.page, Data.page_size, Data.site).enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if (response != null){
                    Example example = response.body();
                    if (example != null){
                        callback.onResult(example.getItems(), null, Data.page + 1);
                    }
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.i("error", t.getMessage());
            }
        });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Item> callback) {
        RetrofitClient.getRestAPI().getAnswers(params.key, Data.page_size, Data.site).enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if (response != null){
                    int key = params.key > 1 ? params.key - 1 : null;
                    Example example = response.body();
                    if (example != null){
                        callback.onResult(example.getItems(), key);
                    }
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.i("error", t.getMessage());
            }
        });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Item> callback) {
        RetrofitClient.getRestAPI().getAnswers(params.key, Data.page_size, Data.site).enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                if (response.body() != null){
                    int key = response.body().getHasMore() ? params.key + 1 : null;
                    Example example = response.body();
                    if (example != null){
                        callback.onResult(example.getItems(), key);
                    }
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.i("error", t.getMessage());
            }
        });
    }
}

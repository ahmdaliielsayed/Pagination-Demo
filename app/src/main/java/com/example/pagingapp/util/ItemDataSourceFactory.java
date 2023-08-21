package com.example.pagingapp.util;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.example.pagingapp.model.Item;

public class ItemDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, Item>> mutableLiveData;

    public ItemDataSourceFactory() {
        mutableLiveData = new MutableLiveData<>();
    }

    @NonNull
    @Override
    public DataSource create() {
        ItemDataSource itemDataSource = new ItemDataSource();
        mutableLiveData.postValue(itemDataSource);
        return itemDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Item>> getMutableLiveData() {
        return mutableLiveData;
    }
}

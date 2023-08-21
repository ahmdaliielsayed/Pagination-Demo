package com.example.pagingapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.example.pagingapp.model.Data;
import com.example.pagingapp.model.Item;
import com.example.pagingapp.util.ItemDataSourceFactory;

public class ItemViewModel extends ViewModel {

    public LiveData<PagedList<Item>> pagedListLiveData;
    public MutableLiveData<PageKeyedDataSource<Integer, Item>> dataSourceLiveData;

    public ItemViewModel() {
        // from factory
        ItemDataSourceFactory itemDataSourceFactory = new ItemDataSourceFactory();
        dataSourceLiveData = itemDataSourceFactory.getMutableLiveData();

        // setEnablePlaceholders (true or false) ==> if true it makes like facebook ... show empty places until data downloaded from server
        PagedList.Config config = new PagedList.Config.Builder().setEnablePlaceholders(false).setPageSize(Data.page_size).build();
        pagedListLiveData = new LivePagedListBuilder<Integer, Item>(itemDataSourceFactory, config).build();
    }
}

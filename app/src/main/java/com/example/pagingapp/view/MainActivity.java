package com.example.pagingapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.pagingapp.R;
import com.example.pagingapp.model.Item;
import com.example.pagingapp.viewmodel.ItemViewModel;

public class MainActivity extends AppCompatActivity {

    ItemViewModel itemViewModel;
    private ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ItemAdapter();
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        itemViewModel.pagedListLiveData.observe(this, new Observer<PagedList<Item>>() {
            @Override
            public void onChanged(PagedList<Item> items) {
                adapter.submitList(items);
            }
        });
    }
}

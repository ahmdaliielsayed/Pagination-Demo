package com.example.pagingapp.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pagingapp.R;
import com.example.pagingapp.model.Item;

public class ItemAdapter extends PagedListAdapter<Item, ItemAdapter.ItemHolder> {

    private Context context;
    public ItemAdapter() {
        super(itemCallback);
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ItemHolder(LayoutInflater.from(context).inflate(R.layout.rv_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ItemHolder holder, int position) {
        Item item = getItem(position);
        if (item != null) {
            Glide.with(context).asBitmap().load(item.getOwner().getProfileImage()).into(holder.imageView);
            holder.textView.setText(item.getOwner().getDisplayName());
        }
    }

    public static DiffUtil.ItemCallback<Item> itemCallback = new DiffUtil.ItemCallback<Item>() {
        @Override
        public boolean areItemsTheSame(@NonNull Item oldItem, @NonNull Item newItem) {
            return oldItem.getAnswerId() == newItem.getAnswerId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Item oldItem, @NonNull Item newItem) {
            return oldItem.equals(newItem);
        }
    };

    class ItemHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageView;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewName);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}

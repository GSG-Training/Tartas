package com.example.tartas.view.welcome;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tartas.R;

import java.util.ArrayList;
import java.util.List;

public class MyPager2Adapter extends RecyclerView.Adapter<MyPager2Adapter.ImagesHolder> {

    private List<ItemPager> mItems;

    public MyPager2Adapter(ArrayList<ItemPager> items) {
        mItems = items;
    }

    @NonNull
    @Override
    public ImagesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ImagesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImagesHolder holder, int position) {
        holder.bind(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ImagesHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView1;
        TextView textView2;
        public ImagesHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewWelcome);
            textView1 = itemView.findViewById(R.id.textViewWelcome1);
            textView2 = itemView.findViewById(R.id.textViewWelcome11);

        }

        public void bind(ItemPager item) {
            imageView.setImageResource(item.getImageView());
            textView1.setText(item.getTextView1());
            textView2.setText(item.getTextView2());

        }
    }
}


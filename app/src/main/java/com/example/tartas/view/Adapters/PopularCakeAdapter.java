package com.example.tartas.view.Adapters;

import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tartas.R;
import com.example.tartas.model.MostPopularCake;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PopularCakeAdapter extends RecyclerView.Adapter<PopularCakeAdapter.PopularCakeViewHolder> {

    private List<MostPopularCake> mItems;
    private Context mContext;

    public PopularCakeAdapter(Context context, List<MostPopularCake> items) {
        mItems = items;
    }

    public PopularCakeAdapter() {
    }


    @NonNull
    @Override
    public PopularCakeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_most_popular, parent, false);
        return new PopularCakeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularCakeViewHolder holder, int position) {
        holder.bind(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class PopularCakeViewHolder extends RecyclerView.ViewHolder {

        private ImageView PopularSaleCakeImage ;
        private TextView PopularNameCake;
        private android.widget.RatingBar PopularRatingBar;
        private TextView PopularPriceCake;


        public PopularCakeViewHolder(@NonNull View itemView) {
            super(itemView);
            PopularSaleCakeImage = itemView.findViewById(R.id.PopularSaleCakeImage);
            PopularNameCake = itemView.findViewById(R.id.PopularNameCake);
            PopularRatingBar = itemView.findViewById(R.id.PopularRatingBar);
            PopularPriceCake = itemView.findViewById(R.id.PopularPriceCake);

        }

        public void bind(MostPopularCake item) {
            // TODO: bind item with view
            if (item.getPopularSaleCakeImage() == null){
                Picasso.get().load(R.drawable.loadgif).placeholder(R.drawable.loadgif).into(PopularSaleCakeImage);

            }else {
                Picasso.get().load(item.getPopularSaleCakeImage()).placeholder(R.drawable.loadgif).into(PopularSaleCakeImage);

            }
            PopularNameCake.setText(item.getPopularNameCake());
            PopularRatingBar.setRating((float) Double.parseDouble(String.valueOf(item.getPopularRatingBar())));
            PopularPriceCake.setText(item.getPopularPriceCake());
        }
    }
}

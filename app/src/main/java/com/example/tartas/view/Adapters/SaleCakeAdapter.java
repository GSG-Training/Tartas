package com.example.tartas.view.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tartas.R;
import com.example.tartas.model.SaleCakeHome;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SaleCakeAdapter extends RecyclerView.Adapter<SaleCakeAdapter.SaleCakeViewHolder> {

    private List<SaleCakeHome> mItems;
    private Context mContext;

    public SaleCakeAdapter(Context context, List<SaleCakeHome> items) {
        mItems = items;
    }

    public SaleCakeAdapter() {
    }



    @NonNull
    @Override
    public SaleCakeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sale_cake, parent, false);
        return new SaleCakeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SaleCakeViewHolder holder, int position) {
        holder.bind(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class SaleCakeViewHolder extends RecyclerView.ViewHolder {

        private TextView NameCake;
        private TextView PriceCake;
        private android.widget.RatingBar ratingBar;
        private TextView DescriptionCake;
        private ImageView SaleCakeImage ;

        public SaleCakeViewHolder(@NonNull View itemView) {
            super(itemView);
            NameCake = itemView.findViewById(R.id.name_cake);
            PriceCake = itemView.findViewById(R.id.price_cake);
            ratingBar = itemView.findViewById(R.id.simpleRatingBar_home);
            DescriptionCake = itemView.findViewById(R.id.description_cake);
            SaleCakeImage = itemView.findViewById(R.id.image_sale_cake);


        }

        public void bind(SaleCakeHome item) {
            // TODO: bind item with view
            NameCake.setText(item.getNameCake());
            PriceCake.setText(item.getPriceCake());
            ratingBar.setRating((float) Double.parseDouble(String.valueOf(item.getRatingBar())));
            DescriptionCake.setText(item.getDescriptionCake());
            if (item.getSaleCakeImage() == null){
                Picasso.get().load(R.drawable.loadgif).placeholder(R.drawable.loadgif).into(SaleCakeImage);

            }else {
                Picasso.get().load(item.getSaleCakeImage()).placeholder(R.drawable.loadgif).into(SaleCakeImage);

            }
        }
    }
}

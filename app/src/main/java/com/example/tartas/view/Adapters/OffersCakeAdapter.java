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
import com.example.tartas.model.OffersCakes;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OffersCakeAdapter extends RecyclerView.Adapter<OffersCakeAdapter.OffersCakesViewHolder> {

    private List<OffersCakes> mItems;
    private Context mContext;

    public OffersCakeAdapter(Context context, List<OffersCakes> items) {
        mItems = items;
    }

    public OffersCakeAdapter() {
    }



    @NonNull
    @Override
    public OffersCakesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle_cakes, parent, false);
        return new OffersCakesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OffersCakesViewHolder holder, int position) {
        holder.bind(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class OffersCakesViewHolder extends RecyclerView.ViewHolder {

        private TextView OffersNameCake;
        private TextView OffersPriceCake;
        private RatingBar OffersRatingBar;
        private TextView OffersCakesDescription;
        private ImageView OffersCakesImage ;

        public OffersCakesViewHolder(@NonNull View itemView) {
            super(itemView);

            OffersCakesImage = itemView.findViewById(R.id.image_cakes);
            OffersNameCake = itemView.findViewById(R.id.name_cakes);
            OffersRatingBar = itemView.findViewById(R.id.simpleRatingBar_cakes);
            OffersCakesDescription = itemView.findViewById(R.id.description_cakes);
            OffersPriceCake = itemView.findViewById(R.id.price_cakes);



        }

        public void bind(OffersCakes item) {
            // TODO: bind item with view
            OffersNameCake.setText(item.getOffersNameCake());
            OffersPriceCake.setText(item.getOffersPriceCake());
            OffersRatingBar.setRating((float) Double.parseDouble(String.valueOf(item.getOffersRatingBar())));
            OffersCakesDescription.setText(item.getOffersCakesDescription());
            if (item.getOffersCakesImage() == null){
                Picasso.get().load(R.drawable.loadgif).placeholder(R.drawable.loadgif).into(OffersCakesImage);

            }else {
                Picasso.get().load(item.getOffersCakesImage()).placeholder(R.drawable.loadgif).into(OffersCakesImage);

            }
        }
    }
}

package com.example.tartas.view.MainActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.tartas.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


public class CakesFragment extends Fragment {

    ProgressBar bar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_cakes, container, false);
        bar = v.findViewById(R.id.progress);

        ImageView OffersImageCake = v.findViewById(R.id.OffersImageCake);
        TextView OffersNameCake = v.findViewById(R.id.OffersNameCake);
        RatingBar OffersRatingBar = v.findViewById(R.id.OffersRatingBar);
        TextView OffersDescriptionCake = v.findViewById(R.id.OffersDescriptionCake);
        TextView OffersPriceCake = v.findViewById(R.id.OffersPriceCake);
        TextView OffersOldPriceCake = v.findViewById(R.id.OffersOldPriceCake);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Special Offers");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                OffersNameCake.setText(snapshot.child("OffersNameCake").getValue()+"");
                OffersRatingBar.setRating(Float.parseFloat(snapshot.child("OffersRatingBar").getValue() + ""));
                OffersDescriptionCake.setText(snapshot.child("OffersDescriptionCake").getValue()+"");
                OffersPriceCake.setText(snapshot.child("OffersPriceCake").getValue()+"");
                OffersOldPriceCake.setText(snapshot.child("OffersOldPriceCake").getValue()+"");

                String message = snapshot.child("OffersImageCake").getValue(String.class);
                Picasso.get()
                        .load(message)
                        .into(OffersImageCake);
                bar.setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return v;

    }
}
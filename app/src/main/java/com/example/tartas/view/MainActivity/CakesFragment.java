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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tartas.R;
import com.example.tartas.model.OffersCakes;
import com.example.tartas.view.Adapters.OffersCakeAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class CakesFragment extends Fragment {

    ProgressBar bar;
    RecyclerView recycle_cakes;
    List<OffersCakes> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_cakes, container, false);
        bar = v.findViewById(R.id.progress);

        recycle_cakes =  v.findViewById(R.id.recycle_cakes);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext() ,2);
        recycle_cakes.setLayoutManager(gridLayoutManager);
        recycle_cakes.setHasFixedSize(true);
        list = new ArrayList<>();
        OffersCakeAdapter offersCakeAdapter = new OffersCakeAdapter(getContext(),list);
        recycle_cakes.setAdapter(offersCakeAdapter);

        ImageView OffersImageCake = v.findViewById(R.id.SpecialImageCake);
        TextView OffersNameCake = v.findViewById(R.id.SpecialNameCake);
        RatingBar OffersRatingBar = v.findViewById(R.id.SpecialRatingBar);
        TextView OffersDescriptionCake = v.findViewById(R.id.SpecialDescriptionCake);
        TextView OffersPriceCake = v.findViewById(R.id.OffersPriceCake);
        TextView OffersOldPriceCake = v.findViewById(R.id.SpecialOldPriceCake);

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

        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference().child("recycle-cakess");
        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    OffersCakes offersCakes = new OffersCakes();
                    offersCakes.setOffersNameCake(dataSnapshot.child("OffersNameCake").getValue()+"");
                    offersCakes.setOffersCakesDescription(dataSnapshot.child("OffersCakesDescription").getValue()+"");
                    offersCakes.setOffersPriceCake(dataSnapshot.child("OffersPriceCake").getValue()+"");
                    offersCakes.setOffersRatingBar(Double.parseDouble(dataSnapshot.child("OffersRatingBar").getValue()+""));
                    offersCakes.setOffersCakesImage(dataSnapshot.child("OffersCakesImage").getValue()+"");
                    list.add(offersCakes);
                    recycle_cakes.setAdapter(offersCakeAdapter);
                    offersCakeAdapter.notifyDataSetChanged();
                    bar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return v;

    }
}
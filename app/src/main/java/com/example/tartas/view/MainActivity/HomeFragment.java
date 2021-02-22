package com.example.tartas.view.MainActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.tartas.R;
import com.example.tartas.model.MostPopularCake;
import com.example.tartas.model.SaleCakeHome;
import com.example.tartas.view.Adapters.PopularCakeAdapter;
import com.example.tartas.view.Adapters.SaleCakeAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeFragment context;
    RecyclerView recyclerHome;
    SaleCakeAdapter saleCakeAdapter;
    List<SaleCakeHome> list;
    ProgressBar bar;
    RecyclerView recyclerMostCakePopular;
    List<MostPopularCake>listMostPopular;
    PopularCakeAdapter popularCakeAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_home, container, false);
        context = this;
        TextView TodayNameCake = v.findViewById(R.id.TodayNameCake);
        TextView TodayPriceCake = v.findViewById(R.id.TodayPriceCake);
        RatingBar TodayRatingBar = v.findViewById(R.id.TodayRatingBar);
        TextView TodayDescriptionCake = v.findViewById(R.id.TodayDescriptionCake);
        ImageView TodayImageCake = v.findViewById(R.id.TodayImageCake);

        recyclerHome = v.findViewById(R.id.sale_cake_recycler);
        bar = v.findViewById(R.id.progress);
        list = new ArrayList<>();
        saleCakeAdapter = new SaleCakeAdapter(getContext(),list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerHome.setHasFixedSize(true);
        recyclerHome.setLayoutManager(manager);

        recyclerMostCakePopular = v.findViewById(R.id.recycle_cake_Popular);
        listMostPopular = new ArrayList<>();
        popularCakeAdapter = new PopularCakeAdapter(getContext(),listMostPopular);
        LinearLayoutManager manager1 = new LinearLayoutManager(getContext());
        manager1.setOrientation(RecyclerView.HORIZONTAL);
        recyclerMostCakePopular.setLayoutManager(manager1);
        recyclerMostCakePopular.setHasFixedSize(true);


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Cake");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    SaleCakeHome saleCakeHome = new SaleCakeHome();
                    saleCakeHome.setNameCake(dataSnapshot.child("NameCake").getValue()+"");
                    saleCakeHome.setDescriptionCake(dataSnapshot.child("DescriptionCake").getValue()+"");
                    saleCakeHome.setPriceCake(dataSnapshot.child("PriceCake").getValue()+"");
                    saleCakeHome.setRatingBar(Double.parseDouble(dataSnapshot.child("RatingBar").getValue()+""));
                    saleCakeHome.setSaleCakeImage(dataSnapshot.child("SaleCakeImage").getValue()+"");
                    list.add(saleCakeHome);
                    recyclerHome.setAdapter(saleCakeAdapter);
                    saleCakeAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference().child("Today'sSpecial");
        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                TodayNameCake.setText(snapshot.child("Today'sNameCake").getValue()+"");
                TodayPriceCake.setText(snapshot.child("Today'sPriceCake").getValue() + "");
                TodayRatingBar.setRating(Float.parseFloat(snapshot.child("TwoRatingBar").getValue() + ""));
                TodayDescriptionCake.setText(snapshot.child("Today'sDescriptionCake").getValue() + "");

                String message = snapshot.child("Today'sImageCake").getValue(String.class);
                Picasso.get()
                        .load(message)
                        .into(TodayImageCake);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        DatabaseReference reference3 = FirebaseDatabase.getInstance().getReference().child("MostCakePopular");
        reference3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    MostPopularCake mostPopularCake = new MostPopularCake();
                    mostPopularCake.setPopularSaleCakeImage(dataSnapshot.child("PopularCakeImage").getValue()+"");
                    mostPopularCake.setPopularNameCake(dataSnapshot.child("PopularNameCake").getValue()+"");
                    mostPopularCake.setPopularRatingBar(Double.parseDouble(dataSnapshot.child("PopularRatingBar").getValue()+""));
                    mostPopularCake.setPopularPriceCake(dataSnapshot.child("PopularPriceCake").getValue()+"");
                    listMostPopular.add(mostPopularCake);
                    recyclerMostCakePopular.setAdapter(popularCakeAdapter);
                    popularCakeAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    return v;
    }

}
package com.example.tartas.view.MainActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.tartas.R;
import com.example.tartas.model.SaleCakeHome;
import com.example.tartas.view.Adapters.SaleCakeAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView recyclerHome;
    SaleCakeAdapter adapterHome;
    List<SaleCakeHome> list;
    ProgressBar bar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_home, container, false);

        recyclerHome = v.findViewById(R.id.sale_cake_recycler);
        bar = v.findViewById(R.id.progress);
        list = new ArrayList<>();
        adapterHome = new SaleCakeAdapter(getContext(),list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerHome.setHasFixedSize(true);
        recyclerHome.setLayoutManager(manager);

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
                    recyclerHome.setAdapter(adapterHome);
                    adapterHome.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    return v;
    }

}
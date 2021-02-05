package com.example.tartas.view.welcome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.tartas.R;
import com.example.tartas.view.sign.SignupActivity;

import java.util.ArrayList;

public class WelcomeActivity extends AppCompatActivity {

    ArrayList<ItemPager> images;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        images = new ArrayList<>();
        images.add(new ItemPager(R.drawable.image1_splash, getString(R.string.text_splash1), getString(R.string.text_splash11)));
        images.add(new ItemPager(R.drawable.image2_splash, getString(R.string.text_splash2), getString(R.string.text_splash22)));
        images.add(new ItemPager(R.drawable.image3_splash, getString(R.string.text_splash3), getString(R.string.text_splash33)));

        ViewPager2 pager = findViewById(R.id.pager);
        pager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        pager.setAdapter(new MyPager2Adapter(images));

    }

    public void onSkipClick(View view) {
        startActivity(new Intent(WelcomeActivity.this, SignupActivity.class));
    }
}
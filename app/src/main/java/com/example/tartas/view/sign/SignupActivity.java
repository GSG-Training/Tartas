package com.example.tartas.view.sign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tartas.R;
import com.example.tartas.view.MainActivity.MainActivity;
import com.google.android.material.button.MaterialButton;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Button buttonsignup = findViewById(R.id.buttonsignup);
        buttonsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, MainActivity.class));

            }
        });
    }

    public void Signin(View view) {
        startActivity(new Intent(SignupActivity.this, SigninActivity.class));
    }
}

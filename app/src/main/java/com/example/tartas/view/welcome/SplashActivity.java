package com.example.tartas.view.welcome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.tartas.R;
import com.example.tartas.view.MainActivity.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.lang.ref.WeakReference;

public class SplashActivity extends AppCompatActivity {

    private MyHandler myHandler;
    private boolean isRunning = true;
    private long currentTimeInMillis;
    private long remaining = 3000;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mAuth = FirebaseAuth.getInstance();
        if (savedInstanceState != null){
            remaining = savedInstanceState.getLong("remaining_delay",2000);
        }
        currentTimeInMillis = System.currentTimeMillis();
        myHandler = new MyHandler(new WeakReference<>(this));
        Message message = myHandler.obtainMessage();
        myHandler.sendMessageDelayed(message , remaining);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isRunning = false;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        remaining -=(System.currentTimeMillis() - currentTimeInMillis);
        outState.putLong("remaining_delay", remaining);
    }

    class MyHandler extends Handler {
        private final WeakReference<SplashActivity> splashActivityWeakReference;

        MyHandler(WeakReference<SplashActivity> splashActivityWeakReference) {
            super();
            this.splashActivityWeakReference = splashActivityWeakReference;
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            SplashActivity splashActivity = splashActivityWeakReference.get();
            if (splashActivity != null){
                isLogin();
                splashActivity.finish();
            }
        }
    }

    public void isLogin(){
        FirebaseUser user = mAuth.getCurrentUser();
        if ( user != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }else {
            startActivity(new Intent(getApplicationContext(),WelcomeActivity.class));
            finish();
        }
    }
}
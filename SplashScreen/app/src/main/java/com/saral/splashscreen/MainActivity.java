package com.saral.splashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = new Intent(MainActivity.this, MainScreen.class);
        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        startActivity(i);
                    }
                }
        , 5000);
    }
}
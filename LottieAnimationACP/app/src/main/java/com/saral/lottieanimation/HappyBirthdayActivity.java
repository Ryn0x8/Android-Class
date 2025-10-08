package com.saral.lottieanimation;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class HappyBirthdayActivity extends AppCompatActivity {
    LottieAnimationView lottieAnimationView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_happy_birthday);
        lottieAnimationView = findViewById(R.id.lottieAnimation);
        lottieAnimationView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(HappyBirthdayActivity.this, "Happy Birthday!!!", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
}
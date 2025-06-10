package com.saral.imageview;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    ImageView change;
    ImageButton tiger;
    ImageButton clickpic;
    ImageButton chameleon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        change = findViewById(R.id.imageView2);
        chameleon = findViewById(R.id.imageButton5);
        tiger = findViewById(R.id.imageButton6);
        clickpic = findViewById(R.id.imageButton7);

        chameleon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener(chameleon);
            }
        });

        tiger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener(tiger);
            }
        });

        clickpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener(clickpic);
            }
        });
    }

    public void onClickListener(ImageButton view){
        change.setImageDrawable(view.getDrawable());
    }
}
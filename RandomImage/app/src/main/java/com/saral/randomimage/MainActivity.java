package com.saral.randomimage;

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
    public Integer state;
    ImageView change;
    ImageButton clickChange;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        state  =0;
        change = findViewById(R.id.imageView);
        clickChange = findViewById(R.id.imageButton);

        clickChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state == 0){
                    clickChange.setImageResource(R.drawable.on);
                    change.setImageResource(R.drawable.off);
                    state = 1;
                }
                else{
                    clickChange.setImageResource(R.drawable.off);
                    change.setImageResource(R.drawable.on);
                    state = 0;
                }
            }
        });

    }
}
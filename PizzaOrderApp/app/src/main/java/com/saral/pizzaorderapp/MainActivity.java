package com.saral.pizzaorderapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    RadioGroup sizeGroup;
    RadioButton size;
    CheckBox mushroom;
    CheckBox pepperoni;
    CheckBox cheese;
    Button order;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        sizeGroup = findViewById(R.id.radiogroup);
        mushroom = findViewById(R.id.checkBox);
        cheese = findViewById(R.id.checkBox2);
        pepperoni = findViewById(R.id.checkBox3);

        order = findViewById(R.id.button);

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sizeGroup.getCheckedRadioButtonId() != -1) {
                    size = findViewById(sizeGroup.getCheckedRadioButtonId());

                } else {
                    Snackbar.make(v, "Please select a size of the pizza", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                Snackbar.make(v, "The pizza of size " + size.getText().toString() + " has been ordered", Snackbar.LENGTH_INDEFINITE).show();
            }

        });

    }
}
package com.saral.gridview;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    GridView gridView;
    String[] subjects = {"English", "Science", "Social", "Maths", "OMaths", "Geography", "Economics", "Account", "Computer","English", "Science", "Social", "Maths", "OMaths", "Geography", "Economics", "Account", "Computer","English", "Science", "Social", "Maths", "OMaths", "Geography", "Economics", "Account", "Computer" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.GridView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, subjects);
        gridView.setAdapter(arrayAdapter);

    }
}
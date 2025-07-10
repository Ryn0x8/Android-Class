package com.saral.splashscreen;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class MainScreen extends AppCompatActivity {
    EditText name;
    EditText age;
    EditText grade;


    Button submit;

    TextView namep;
    TextView gradep;
    TextView agep;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        name = findViewById(R.id.editTextText);
        age = findViewById(R.id.editTextText2);
        grade = findViewById(R.id.editTextText3);

        submit = findViewById(R.id.button);

        namep = findViewById(R.id.Name);
        agep = findViewById(R.id.Age);
        gradep = findViewById(R.id.Grade);

        submit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        namep.setText(name.getText().toString());
                        agep.setText(age.getText().toString());
                        gradep.setText(grade.getText().toString());
                        name.setText("");
                        grade.setText("");
                        age.setText("");
                    }
                }
        );


    }
}
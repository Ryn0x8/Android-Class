package com.saral.addnumbers;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText fnum;
    EditText snum;
    Button add;
    TextView result;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fnum = findViewById(R.id.editTextText);
        snum = findViewById(R.id.editTextText2);
        add = findViewById(R.id.button);
        result = findViewById(R.id.textView2);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fnum.getText().toString().isEmpty() || snum.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Please fill the feilds properly!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Integer fNumber = Integer.valueOf(fnum.getText().toString());
                Integer sNumber = Integer.valueOf(snum.getText().toString());

                result.setText("The answer is : "+ (fNumber+sNumber));

            }
        });
    }
}
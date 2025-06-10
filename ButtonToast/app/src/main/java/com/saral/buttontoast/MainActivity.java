package com.saral.buttontoast;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button showToastButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        showToastButton = findViewById(R.id.showToastButton);

        showToastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToDisplay = editText.getText().toString();

                if (textToDisplay.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter some text!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, textToDisplay, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
package com.saral.namethecountry;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView imageViewFlag;
    private EditText etGuess;
    private Button btnSubmit;

    private String[] countryNames = {"India", "Brazil", "France", "Japan"};
    private int[] countryFlags = {
            R.drawable.india,
            R.drawable.brazil,
            R.drawable.france,
            R.drawable.japan
    };

    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageViewFlag = findViewById(R.id.imageViewFlag);
        etGuess = findViewById(R.id.etGuess);
        btnSubmit = findViewById(R.id.btnSubmit);

        showNextCountry();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String guess = etGuess.getText().toString().trim();

                if (guess.equalsIgnoreCase(countryNames[currentIndex])) {
                    currentIndex++;
                    if (currentIndex < countryNames.length) {
                        showNextCountry();
                    } else {
                        Toast.makeText(MainActivity.this, "Congratulations! Quiz complete.", Toast.LENGTH_LONG).show();
                        btnSubmit.setEnabled(false);
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Incorrect! Try again.", Toast.LENGTH_SHORT).show();
                }
                etGuess.setText("");
            }
        });
    }

    private void showNextCountry() {
        imageViewFlag.setImageResource(countryFlags[currentIndex]);
    }
}

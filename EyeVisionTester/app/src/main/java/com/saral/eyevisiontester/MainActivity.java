package com.saral.eyevisiontester;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView testText, resultText;
    private EditText userInput;
    private Button submitButton;

    private int currentLevel = 0;
    private final int maxLevels = 5;
    private final int[] fontSizes = {48, 40, 32, 24, 18};
    private String currentLetters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testText = findViewById(R.id.testText);
        userInput = findViewById(R.id.userInput);
        submitButton = findViewById(R.id.submitButton);
        resultText = findViewById(R.id.resultText);

        loadNextLevel();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String answer = userInput.getText().toString().trim().toUpperCase();

                if (answer.equals(currentLetters)) {
                    currentLevel++;
                    if (currentLevel < maxLevels) {
                        resultText.setText("Correct! Next Level 🔼");
                        loadNextLevel();
                    } else {
                        resultText.setText("You completed all levels! 🎉");
                        testText.setText("🎯");
                        submitButton.setEnabled(false);
                    }
                } else {
                    resultText.setText("Try again 👀");
                }
            }
        });
    }

    private void loadNextLevel() {
        userInput.setText("");
        resultText.setText("");

        currentLetters = generateRandomLetters(2 + currentLevel);  // More letters each level
        testText.setText(currentLetters);
        testText.setTextSize(fontSizes[currentLevel]);
    }

    private String generateRandomLetters(int length) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();

        for (int i = 0; i < length; i++) {
            if (i > 0) sb.append(" ");
            sb.append(alphabet.charAt(rand.nextInt(alphabet.length())));
        }

        return sb.toString();
    }
}

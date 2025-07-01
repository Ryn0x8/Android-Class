package com.saral.quizapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    TextView question;
    EditText answer;
    Button submit;
    String qtn;
    Quiz quiz;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        question = findViewById(R.id.textView2);
        answer = findViewById(R.id.editTextText);
        submit = findViewById(R.id.button);
        quiz = new Quiz();
        quiz.setQuestion("What is the Capital city of Nepal? ", "kathmandu");
        quiz.setQuestion("What is the national game of Nepal ", "volleyball");
        quiz.setQuestion("What is the national bird of Nepal? ", "danphe");
        quiz.setQuestion("What is the National Flower of Nepal? ", "rhododendron");
        anotherQuestion();

        submit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (answer.getText().toString().toLowerCase().equals(quiz.getAnswer(qtn)) ){
                            Snackbar.make(v, "Correct Answer!", Snackbar.LENGTH_SHORT).show();
                            Log.i("quiz","The user gave answer "+ answer.getText().toString()+" to the question "+ qtn + " which is correct");
                            anotherQuestion();
                        }
                        else{
                            Snackbar.make(v, "Incorrect Answer!", Snackbar.LENGTH_SHORT).show();
                            Log.i("quiz","The user gave answer "+ answer.getText().toString()+" to the question "+ qtn + " which is incorrect");
                            anotherQuestion();
                        }
                    }
                }
        );
    }

    public void anotherQuestion(){
        qtn = quiz.getQuestion();
        question.setText(qtn);
        answer.getText().clear();
    }
}
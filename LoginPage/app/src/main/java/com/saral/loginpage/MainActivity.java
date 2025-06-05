package com.saral.loginpage;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button login;
    EditText username;
    EditText password;
    TextView welcomeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.button);
        username = findViewById(R.id.editTextText);
        password = findViewById(R.id.editTextTextPassword);
        welcomeView = findViewById(R.id.textView2);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Please fill the fields properly!", Toast.LENGTH_SHORT).show();
                    return;
                }
                String userName = username.getText().toString();

                password.setVisibility(View.INVISIBLE);
                username.setVisibility(View.INVISIBLE);
                login.setVisibility(View.INVISIBLE);

                welcomeView.setText("Welcome, Mr/Mrs "+userName);
            }
        });
    }
}
package com.saral.passtheinput;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainPage extends AppCompatActivity {

    TextView displayUsername, displayPassword, displayEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        displayUsername = findViewById(R.id.display_username);
        displayPassword = findViewById(R.id.display_password);
        displayEmail = findViewById(R.id.display_email);

        Intent intent = getIntent();
        String username = intent.getStringExtra("com.saral.passtheinput.username");
        String password = intent.getStringExtra("com.saral.passtheinput.password");
        String email = intent.getStringExtra("com.saral.passtheinput.email");

        displayUsername.setText("Username: " + username);
        displayPassword.setText("Password: " + password);
        displayEmail.setText("Email: " + email);
    }
}

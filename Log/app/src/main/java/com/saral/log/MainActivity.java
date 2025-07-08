package com.saral.log;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "LogApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] messages = {"Welcome to the app", "User clicked login", "Data fetched", "Process completed"};

        for (int i = 0; i < messages.length; i++) {
            Log.d(TAG, "Log " + (i + 1) + ": " + messages[i]);
        }
    }
}

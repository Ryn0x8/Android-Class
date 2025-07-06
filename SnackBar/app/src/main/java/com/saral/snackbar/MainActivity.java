package com.saral.snackbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    String lastSnack = "";
    View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rootView = findViewById(android.R.id.content);

        setupSnackButton(R.id.cookieBtn, "Cookie");
        setupSnackButton(R.id.chocoBtn, "Chocolate");
        setupSnackButton(R.id.friesBtn, "Fries");
        setupSnackButton(R.id.juiceBtn, "Juice");
    }

    private void setupSnackButton(int id, String snackName) {
        Button btn = findViewById(id);
        btn.setOnClickListener(view -> {
            lastSnack = snackName;
            Snackbar.make(rootView, "You chose " + snackName + ".", Snackbar.LENGTH_LONG)
                    .setAction("Undo", v -> {
                        Snackbar.make(rootView, snackName + " selection removed.", Snackbar.LENGTH_SHORT).show();
                    })
                    .show();
        });
    }
}

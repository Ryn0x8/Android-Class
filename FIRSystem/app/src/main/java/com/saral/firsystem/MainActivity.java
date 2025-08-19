package com.saral.firsystem;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText etFirId, etFirInfo;
    Button btnSave, btnRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etFirId = findViewById(R.id.etFirId);
        etFirInfo = findViewById(R.id.etFirInfo);
        btnSave = findViewById(R.id.btnSave);
        btnRead = findViewById(R.id.btnRead);

        btnSave.setOnClickListener(v -> saveFir());
        btnRead.setOnClickListener(v -> readFir());
    }

    private void saveFir() {
        String id = etFirId.getText().toString().trim();
        String info = etFirInfo.getText().toString();

        if (id.isEmpty()) {
            showToast("Please enter FIR ID");
            return;
        }
        if (info.isEmpty()) {
            showToast("Please enter FIR Information");
            return;
        }

        // sanitize filename (only letters, numbers, _, -)
        String safeId = id.replaceAll("[^A-Za-z0-9_-]", "_");
        String fileName = "FIR_" + safeId + ".txt";

        try {
            openFileOutput(fileName, MODE_PRIVATE).write(info.getBytes());
            showToast("FIR saved (ID: " + id + ")");
        } catch (IOException e) {
            e.printStackTrace();
            showToast("Error saving FIR");
        }
    }

    private void readFir() {
        String id = etFirId.getText().toString().trim();

        if (id.isEmpty()) {
            showToast("Enter FIR ID to read");
            return;
        }

        String safeId = id.replaceAll("[^A-Za-z0-9_-]", "_");
        String fileName = "FIR_" + safeId + ".txt";

        try {
            byte[] buffer = new byte[openFileInput(fileName).available()];
            openFileInput(fileName).read(buffer);
            String text = new String(buffer);
            etFirInfo.setText(text);
            showToast("FIR loaded");
        } catch (FileNotFoundException e) {
            showToast("No FIR found for ID: " + id);
        } catch (IOException e) {
            e.printStackTrace();
            showToast("Error reading FIR");
        }
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}

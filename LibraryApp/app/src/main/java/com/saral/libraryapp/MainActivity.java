package com.saral.libraryapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText etBookCode, etBookName, etAuthorName, etBookPrice;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etBookCode = findViewById(R.id.etBookCode);
        etBookName = findViewById(R.id.etBookName);
        etAuthorName = findViewById(R.id.etAuthorName);
        etBookPrice = findViewById(R.id.etBookPrice);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveBook();
            }
        });
    }

    private void saveBook() {
        String code = etBookCode.getText().toString();
        String name = etBookName.getText().toString();
        String author = etAuthorName.getText().toString();
        String price = etBookPrice.getText().toString();

        String data = "Book Name: " + name + "\nAuthor: " + author + "\nPrice: " + price;

        try (FileOutputStream fos = openFileOutput(code + ".txt", MODE_PRIVATE)) {
            fos.write(data.getBytes());
            Toast.makeText(this, "Book saved successfully!", Toast.LENGTH_SHORT).show();

            etBookCode.setText("");
            etBookName.setText("");
            etAuthorName.setText("");
            etBookPrice.setText("");

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error saving book", Toast.LENGTH_SHORT).show();
        }
    }
}

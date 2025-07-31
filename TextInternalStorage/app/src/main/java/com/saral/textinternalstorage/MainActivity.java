package com.saral.textinternalstorage;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    EditText fileName;
    EditText fileContent;
    Button create;
    Button imgCreate;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        fileName = findViewById(R.id.editTextText);
        fileContent = findViewById(R.id.editTextText2);
        create = findViewById(R.id.button);
        imgCreate = findViewById(R.id.button2);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fileName.getText().toString().isEmpty() || fileContent.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                    return;
                    }
                save();
            }
        });

        imgCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveImage();
            }
        });
    }

    public void save() {
        String FileName = fileName.getText().toString() + ".txt";
        String FileContent = fileContent.getText().toString();

        FileOutputStream fileOutputStream = null;

        try{
            fileOutputStream = openFileOutput(FileName, MODE_PRIVATE);
            fileOutputStream.write(FileContent.getBytes());
            String filePath = getFilesDir().getAbsolutePath() + "/" + FileName;
            Toast.makeText(this, "Saved to: " + filePath, Toast.LENGTH_LONG).show();


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void SaveImage(){
        String imgName = "dog";
        FileOutputStream fileOutputStream = null;
        @SuppressLint("UseCompatLoadingForDrawables") BitmapDrawable bitmapDrawable= (BitmapDrawable) getDrawable(R.drawable.dog);
        Bitmap data = bitmapDrawable.getBitmap();
        try{
            fileOutputStream = openFileOutput(imgName + ".jpg", MODE_PRIVATE);
            data.compress(Bitmap.CompressFormat.JPEG, 50, fileOutputStream);
            String filePath = getFilesDir().getAbsolutePath() + "/" + imgName + ".jpg";
            Toast.makeText(this, "Saved to: " + filePath, Toast.LENGTH_LONG).show();


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
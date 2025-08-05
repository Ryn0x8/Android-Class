package com.saral.loadstorefiles;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Load extends AppCompatActivity {
    EditText fileName;
    Button textFile;
    Button imgFile;
    TextView textContent;
    ImageView imageView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_load);

        textFile = findViewById(R.id.button3);
        imgFile = findViewById(R.id.button4);
        imageView = findViewById(R.id.imageView);
        fileName = findViewById(R.id.editTextText3);
        textContent  = findViewById(R.id.textView2);

        textFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fileName.getText().toString().isEmpty()){
                    Toast.makeText(Load.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                loadTextFile(v);
            }
        });

        imgFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fileName.getText().toString().isEmpty()){
                    Toast.makeText(Load.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                loadImgFile(v);
            }
        });


    }

    public void loadTextFile(View v){
        FileInputStream fileInputStream = null;
        try{
            fileInputStream = openFileInput(fileName.getText().toString());
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String text;
            while ((text = bufferedReader.readLine())!= null){
                stringBuilder.append(text).append("\n");
            }
            textContent.setText(stringBuilder.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, "Enter a valid file name", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void loadImgFile(View v){
        Bitmap bitmap = null;

        String fName;

        fName = fileName.getText().toString();
        if (!fName.contains(".jpg")){
            fName = fName+".jpg";
        }

        InputStream inputStream;

        try{
            inputStream = openFileInput(fName);
            bitmap = BitmapFactory.decodeStream(inputStream);
            imageView.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, "Enter a valid file name", Toast.LENGTH_SHORT).show();
        }
    }
}
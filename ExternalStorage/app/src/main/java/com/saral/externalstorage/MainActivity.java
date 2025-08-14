package com.saral.externalstorage;

import android.annotation.SuppressLint;
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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    EditText filename;
    EditText fileContent;
    Button save;
    Button load;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        filename = findViewById(R.id.fileName);
        fileContent = findViewById(R.id.fileContent);
        save = findViewById(R.id.button);
        load = findViewById(R.id.button2);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createFiletoExternalPrivate(filename.getText().toString());
            }
        });

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File(getExternalFilesDir(null), filename.getText().toString());
                if (file.exists()){
                    load(file);
                    Toast.makeText(MainActivity.this, "File loaded: " + file.getName(), Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "File not found!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void createFiletoExternalPrivate(String filename){
        File file = new File(getExternalFilesDir(null), filename);
        save(file);
    }

    private void save(File file) {
        String filecontent = fileContent.getText().toString();
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(filecontent.getBytes());
            fileContent.getText().clear();
            filename.getText().clear();
            Toast.makeText(this, "Saved file: "+ file.getName() + " Path: "+ file.getPath(), Toast.LENGTH_LONG).show();

            
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (fileOutputStream != null){
                try{
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private String load(File file){
        FileInputStream fileInputStream = null;
        StringBuilder stringBuilder = new StringBuilder();
        try{
            fileInputStream = new FileInputStream(file);
            int text;
            while ((text = fileInputStream.read())!= -1){
                stringBuilder.append((char) text);
            }
            fileContent.setText(stringBuilder.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fileInputStream != null){
                try{
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return stringBuilder.toString();
    }
}
package com.saral.insertvaluetodb;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText name, surname, marks, id;
    Button save,load, update, drop;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.editTextText);
        surname = findViewById(R.id.editTextText2);
        marks = findViewById(R.id.editTextText3);
        id = findViewById(R.id.editTextText4);
        save = findViewById(R.id.button);
        load = findViewById(R.id.button2);
        update = findViewById(R.id.button3);
        drop = findViewById(R.id.button4);

        try (DatabaseHelper db = new DatabaseHelper(this)) {
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!name.getText().toString().isEmpty() && !surname.getText().toString().isEmpty() && !marks.getText().toString().isEmpty()) {
                        db.insertData(name.getText().toString(), surname.getText().toString(), marks.getText().toString());
                    } else {
                        Toast.makeText(MainActivity.this, "Please fill up all the fields", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            load.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Cursor res = db.getAllData();
                    if (res.getCount() == 0){
                        showMessage("Error", "Nothing Found");
                        return;
                    }
                    StringBuffer stringBuffer = new StringBuffer();
                    while (res.moveToNext()){
                        stringBuffer.append("ID: "+ res.getString(0)+ "\n");
                        stringBuffer.append("Name: "+ res.getString(1)+ "\n");
                        stringBuffer.append("Surname: "+ res.getString(2)+ "\n");
                        stringBuffer.append("Marks: "+ res.getString(3)+ "\n");
                    }
                    showMessage("Data", stringBuffer.toString());
                }
            });

            update.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!id.getText().toString().isEmpty() && !name.getText().toString().isEmpty() && !surname.getText().toString().isEmpty() && !marks.getText().toString().isEmpty()) {
                                boolean isUpdated = db.updateData(id.getText().toString(), name.getText().toString(), surname.getText().toString(), marks.getText().toString());
                                if (isUpdated) {
                                    Toast.makeText(MainActivity.this, "Data Successfully Updated", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(MainActivity.this, "Data Failed to Update", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
            );

            drop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!id.getText().toString().isEmpty()){
                        Integer deletedRows = db.deleteData(id.getText().toString());
                        if (deletedRows>0){
                            Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this, "Failed to Delete Data", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(MainActivity.this, "Please Fill out All the fields", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void showMessage(String Title, String Description){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(Title);
        builder.setMessage(Description);
        builder.show();
    }
}
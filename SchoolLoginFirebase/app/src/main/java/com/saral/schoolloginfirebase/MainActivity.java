package com.saral.schoolloginfirebase;

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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText editName, editEmail, editNumber, editSchool;
    Button btnAdd;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.editTextName);
        editEmail = findViewById(R.id.editTextEmail);
        editNumber = findViewById(R.id.editTextNumber);
        editSchool = findViewById(R.id.editTextSchool);
        btnAdd = findViewById(R.id.btnAddData);

        databaseReference = FirebaseDatabase.getInstance().getReference("Students");

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editName.getText().toString();
                String email = editEmail.getText().toString();
                String number = editNumber.getText().toString();
                String school = editSchool.getText().toString();

                String id = databaseReference.push().getKey();
                Student student = new Student(name, email, number, school);

                databaseReference.child(id).setValue(student)
                        .addOnSuccessListener(unused ->
                                Toast.makeText(MainActivity.this, "Data added successfully!", Toast.LENGTH_SHORT).show()
                        )
                        .addOnFailureListener(e ->
                                Toast.makeText(MainActivity.this, "Failed: " + e.getMessage(), Toast.LENGTH_SHORT).show()
                        );
            }
        });
    }
}

package com.saral.firebaselogin;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.*;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    EditText name, email;
    Button add;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EdgeToEdge.enable(this);

        name = findViewById(R.id.editTextText2);
        email = findViewById(R.id.editTextTextEmailAddress2);
        add = findViewById(R.id.button2);

        reference = FirebaseDatabase.getInstance().getReference().child("Student");



        add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String fullname = name.getText().toString();
                        String emailadd = email.getText().toString();

                        if (TextUtils.isEmpty(fullname)){
                            Toast.makeText(MainActivity.this, "Enter your name", Toast.LENGTH_SHORT).show();
                        } else if (TextUtils.isEmpty(emailadd)) {
                            Toast.makeText(MainActivity.this, "Enter your email", Toast.LENGTH_SHORT).show();

                        }
                        else {
                            Student student = new Student(fullname, emailadd);
                            String uniqueid = reference.push().getKey();
                            reference.child(Objects.requireNonNull(uniqueid)).setValue(student).addOnCompleteListener(
                                    new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){
                                                Toast.makeText(MainActivity.this, "Data Added Successfully", Toast.LENGTH_SHORT).show();
                                                name.setText("");
                                                email.setText("");
                                            }
                                            else{
                                                Toast.makeText(MainActivity.this, "Failed to insert data", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
                            );
                        }
                    }
                }
        );
    }
}
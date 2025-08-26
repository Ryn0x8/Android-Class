package com.saral.hospitalmanagementsystem;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RecordPatients extends AppCompatActivity {
    EditText name, age, contact, address;
    Spinner gender;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_record_patients);
        name = findViewById(R.id.et_patient_name);
        age = findViewById(R.id.et_patient_age);
        contact = findViewById(R.id.et_patient_contact);
        address = findViewById(R.id.et_patient_address);
        gender = findViewById(R.id.spinner_gender);
        String[] genders = {"Select Gender", "Male", "Female", "Other"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, genders);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(adapter);
        gender.setSelection(0);
        save = findViewById(R.id.btn_save_patient);

        try(DBHelper db = new DBHelper(this)){
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!name.getText().toString().isEmpty() && !age.getText().toString().isEmpty() && !contact.getText().toString().isEmpty()&& !address.getText().toString().isEmpty() && !gender.getSelectedItem().toString().equals("Select Gender")){
                        boolean res = db.insertData(name.getText().toString(), age.getText().toString(),   contact.getText().toString(), address.getText().toString(), gender.getSelectedItem().toString());
                        if (res){
                            Toast.makeText(RecordPatients.this, "Successfully recorded the patients data", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(RecordPatients.this, "There was an error recording the data", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(RecordPatients.this, "Please fill out all the fields", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
}
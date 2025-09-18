package com.saral.ecommerce;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DBHelper dbHelper;
    EditText edtId, edtName, edtRetailer, edtPrice;
    Button btnInsert, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);

        edtId = findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtName);
        edtRetailer = findViewById(R.id.edtRetailer);
        edtPrice = findViewById(R.id.edtPrice);
        btnInsert = findViewById(R.id.btnInsert);
        btnDelete = findViewById(R.id.btnDelete);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(edtId.getText().toString());
                String name = edtName.getText().toString();
                String retailer = edtRetailer.getText().toString();
                double price = Double.parseDouble(edtPrice.getText().toString());
                boolean inserted = dbHelper.insertProduct(id, name, retailer, price);
                if (inserted)
                    Toast.makeText(MainActivity.this, "Product Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Insertion Failed", Toast.LENGTH_SHORT).show();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double price = Double.parseDouble(edtPrice.getText().toString());
                int deleted = dbHelper.deleteByPrice(price);
                if (deleted > 0)
                    Toast.makeText(MainActivity.this, deleted + " Product(s) Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "No Product Found with Price " + price, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

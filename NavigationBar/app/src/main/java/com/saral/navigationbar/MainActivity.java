package com.saral.navigationbar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout rootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootLayout = findViewById(R.id.rootLayout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true; // Show the menu in overflow (3-dot)
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_red) {
            rootLayout.setBackgroundColor(Color.RED);
            return true;
        } else if (id == R.id.action_green) {
            rootLayout.setBackgroundColor(Color.GREEN);
            return true;
        } else if (id == R.id.action_blue) {
            rootLayout.setBackgroundColor(Color.BLUE);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

}

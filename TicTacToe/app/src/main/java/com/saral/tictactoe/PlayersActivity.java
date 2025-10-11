package com.saral.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.saral.tictactoe.databinding.ActivityPlayersBinding;

import java.io.ObjectInputStream;
import java.util.Objects;

public class PlayersActivity extends AppCompatActivity {
    ActivityPlayersBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityPlayersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonLetsPlay.setOnClickListener(view -> {
            String playerOne = Objects.requireNonNull(binding.etPlayerOne.getText()).toString();
            String playerTwo = Objects.requireNonNull(binding.etPlayerTwo.getText()).toString();

            if (TextUtils.isEmpty(playerOne)){
                binding.etPlayerOne.setError("Please Enter Player One Name");
            } else if (TextUtils.isEmpty(playerTwo)) {
                binding.etPlayerTwo.setError("Please Enter Player Two Name");
            } else if (playerOne.equals(playerTwo)) {
                Toast.makeText(this, "Please Enter Different Player Names", Toast.LENGTH_SHORT).show();
            }
            else{
                Intent i = new Intent(this, GameActivity.class);
                i.putExtra("playerOne", playerOne);
                i.putExtra("playerTwo", playerTwo);
                startActivity(i);
            }
        });


    }
}
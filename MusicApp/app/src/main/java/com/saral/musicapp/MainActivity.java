package com.saral.musicapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.PixelCopy;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView musicList;
    TextView noSongs, musicText;
    private ArrayList<AudioModal> songsList = new ArrayList<>();
    private static final  int permissionCode = 100;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        musicList = findViewById(R.id.musicList);
        noSongs = findViewById(R.id.noSongs);
        musicText = findViewById(R.id.musicText);

        if (!checkPermission(this)){
            requestPermission(this);
            return;
        }

        getMusicFiles();
        if (songsList.isEmpty()){
            noSongs.setVisibility(View.VISIBLE);
            musicList.setVisibility(View.GONE);
            musicText.setVisibility(View.GONE);
        }else{
            noSongs.setVisibility(View.GONE);
            musicList.setVisibility(View.VISIBLE);
            musicText.setVisibility(View.VISIBLE);
            musicList.setLayoutManager(new LinearLayoutManager(this));
            musicList.setAdapter(new MusicListAdapter(songsList));
        }
    }

    boolean checkPermission(Context c){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            return ContextCompat.checkSelfPermission(c, Manifest.permission.READ_MEDIA_AUDIO) == PackageManager.PERMISSION_GRANTED;
        } else {
            return ContextCompat.checkSelfPermission(c, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        }
    }

    void requestPermission(Activity activity){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_MEDIA_AUDIO}, permissionCode);
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, permissionCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == permissionCode){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getMusicFiles();
                if (!songsList.isEmpty()){
                    musicList.setLayoutManager(new LinearLayoutManager(this));
                    musicList.setAdapter(new MusicListAdapter(songsList));
                    noSongs.setVisibility(View.GONE);
                    musicList.setVisibility(View.VISIBLE);
                    musicText.setVisibility(View.VISIBLE);
                }else{
                    noSongs.setVisibility(View.VISIBLE);
                    musicList.setVisibility(View.GONE);
                    musicText.setVisibility(View.GONE);
                }
            } else {
                Toast.makeText(this,
                        "READ PERMISSION IS REQUIRED, PLEASE ALLOW FROM SETTING.",
                        Toast.LENGTH_LONG).show();
            }
        }
    }


    private void getMusicFiles(){
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projections = {
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.DATA,
        };

        String selection = MediaStore.Audio.Media.IS_MUSIC + "!=0";
        String[] selectionargs = null;
        String sortOrder = null;

        @SuppressLint("Recycle") Cursor cursor = getContentResolver().query(
                uri,
                projections,
                selection,
                selectionargs,
                sortOrder
        );

        if (cursor!=null){
            while(cursor.moveToNext()){
                String title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
                long duration = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));
                String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                boolean songExists = false;
                for (AudioModal song : songsList) {
                    if (song.getPath().equals(path)) {
                        songExists = true;
                        break;
                    }
                }
                if (!songExists){
                    AudioModal song = new AudioModal(path, title, duration);
                    songsList.add(song);
                }
            }
            cursor.close();
        }
        Log.d("MUSIC_FILES", "Total unique songs found: " + songsList.size());
    }
}
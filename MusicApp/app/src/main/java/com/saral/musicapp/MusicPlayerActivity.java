package com.saral.musicapp;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.util.ArrayList;

public class MusicPlayerActivity extends AppCompatActivity {
    TextView musicTitle, currentDuration, totalTime;
    SeekBar seekBar;
    ImageView musicLogo, pause, next, previous;
    ArrayList<AudioModal> songsList;
    AudioModal currentSong;
    MediaPlayer mediaPlayer = MyMediaPlayer.getInstance();
    int rotation = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_music_player);
        musicTitle = findViewById(R.id.song_title);
        currentDuration = findViewById(R.id.current_time);
        totalTime = findViewById(R.id.total_time);
        seekBar = findViewById(R.id.seek_bar);
        musicLogo = findViewById(R.id.music_logo);
        pause = findViewById(R.id.pause);
        next = findViewById(R.id.next);
        previous = findViewById(R.id.previous);
        songsList = (ArrayList<AudioModal>) getIntent().getSerializableExtra("SONGS_LIST");
        musicTitle.setSelected(true);
        setResourcesWithMusic();
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer!=null){
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    currentDuration.setText(MusicListAdapter.formatDuration(mediaPlayer.getCurrentPosition()));
                    if (mediaPlayer.isPlaying()){
                        pause.setImageResource(R.drawable.pauselogo);
                        musicLogo.setRotation(rotation ++);
                    }else{
                        pause.setImageResource(R.drawable.playlogo);
                        musicLogo.setRotation(0);
                    }
                }
                new Handler().postDelayed(this, 10);
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (mediaPlayer != null && b){
                    mediaPlayer.seekTo(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.setOnCompletionListener(null);
                    mediaPlayer.reset();
                    mediaPlayer.stop();
                }
                finish();
            }
        });

    }

    private void setResourcesWithMusic(){
        currentSong = songsList.get(MyMediaPlayer.currentIndex);
        musicTitle.setText(currentSong.getTitle());
        totalTime.setText(MusicListAdapter.formatDuration(currentSong.getDuration()));
        musicTitle.setText(currentSong.getTitle());
        next.setOnClickListener(view -> playNextSong());
        previous.setOnClickListener(view -> playPreviousSong());
        pause.setOnClickListener(view -> pauseSong());
        playMusic();
    }

    private void playMusic(){
        mediaPlayer.reset();
        try{
            mediaPlayer.setDataSource(currentSong.getPath());
            mediaPlayer.prepare();
            mediaPlayer.start();
            seekBar.setProgress(0);
            seekBar.setMax(mediaPlayer.getDuration());
            mediaPlayer.setOnCompletionListener(mp -> {
                playNextSong();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void playNextSong(){
        if (MyMediaPlayer.currentIndex == songsList.size()-1){
            MyMediaPlayer.currentIndex = 0;
            mediaPlayer.reset();
            setResourcesWithMusic();
            return;
        }
        MyMediaPlayer.currentIndex+=1;
        mediaPlayer.reset();
        setResourcesWithMusic();
    }

    private void playPreviousSong(){
        if (MyMediaPlayer.currentIndex == 0){
            MyMediaPlayer.currentIndex=songsList.size()-1;
            mediaPlayer.reset();
            setResourcesWithMusic();
            return;
        }
        MyMediaPlayer.currentIndex-=1;
        mediaPlayer.reset();
        setResourcesWithMusic();
    }

    private void pauseSong(){
        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
        else{
            mediaPlayer.start();
        }
    }


}
package com.saral.musicapp;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.MusicViewHolder> {
    ArrayList<AudioModal> songs;

    public MusicListAdapter(ArrayList<AudioModal> songs) {
        this.songs = songs;
    }


    @NonNull
    @Override
    public MusicListAdapter.MusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_item, parent, false);
         return new MusicViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MusicListAdapter.MusicViewHolder holder, int position) {
        AudioModal song = songs.get(position);
        holder.name.setText(song.getTitle());
        if (MyMediaPlayer.currentIndex == holder.getBindingAdapterPosition()){
            holder.name.setTextColor(Color.parseColor("#FF0000"));
        }
        else {
            holder.name.setTextColor(Color.parseColor("#000000"));

        }
        String durationStr = formatDuration(song.getDuration());
        holder.duration.setText(durationStr);
        holder.itemView.setOnClickListener(v -> {
            int pos = holder.getBindingAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {
                AudioModal clickedSong = songs.get(pos);
                MediaPlayer mediaPlayer = MyMediaPlayer.getInstance();
                mediaPlayer.reset();
                MyMediaPlayer.currentIndex = pos;
                Intent intent = new Intent(v.getContext(), MusicPlayerActivity.class);
                intent.putExtra("SONGS_LIST", songs);
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public static class MusicViewHolder extends RecyclerView.ViewHolder{
        TextView name,duration;
        public MusicViewHolder(@NonNull View itemView) {
            super(itemView);
            name  = itemView.findViewById(R.id.name);
            duration = itemView.findViewById(R.id.duration);
        }
    }

    public static String formatDuration(long millis) {
        long seconds = millis / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;

        seconds %= 60;
        minutes %= 60;
        hours %= 24;

        if (days > 0) {
            return String.format(Locale.getDefault(), "%d:%02d:%02d:%02d", days, hours, minutes, seconds);
        } else if (hours > 0) {
            return String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, seconds);
        } else {
            return String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        }
    }

}

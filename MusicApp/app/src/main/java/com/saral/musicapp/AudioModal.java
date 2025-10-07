package com.saral.musicapp;

import java.io.Serializable;

public class AudioModal implements Serializable {
    private String path, title;
    private Long duration;

    public AudioModal() {
    }

    public AudioModal(String path, String title, Long duration) {
        this.path = path;
        this.title = title;
        this.duration = duration;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }
}

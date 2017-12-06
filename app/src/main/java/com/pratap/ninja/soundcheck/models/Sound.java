package com.pratap.ninja.soundcheck.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by darsh on 6/12/17.
 */

public class Sound {

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("genre")
    @Expose
    private String genre;

    @SerializedName("stream_url")
    @Expose
    private String streamUrl;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setStreamUrl(String streamUrl) {
        this.streamUrl = streamUrl;
    }

    public String getTitle() {

        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getStreamUrl() {
        return streamUrl;
    }

    public Sound(String title, String genre, String streamUrl) {

        this.title = title;
        this.genre = genre;
        this.streamUrl = streamUrl;
    }
}

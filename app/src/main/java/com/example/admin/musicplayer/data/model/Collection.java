package com.example.admin.musicplayer.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TamTT on 9/14/2018.
 */

public class Collection {
    @SerializedName("track")
    @Expose
    private Track mTrack;
    @SerializedName("score")
    @Expose
    private int mScore;

    public Track getTrack() {
        return mTrack;
    }

    public void setTrack(Track track) {
        this.mTrack = track;
    }

    public int getScore() {
        return mScore;
    }

    public void setScore(int score) {
        this.mScore = score;
    }

    @Override
    public String toString() {
        return "Collection{" +
                "mTrack=" + mTrack +
                ", mScore=" + mScore +
                '}';
    }
}

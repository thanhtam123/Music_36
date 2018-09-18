package com.example.admin.musicplayer.data.model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TamTT on 9/14/2018.
 */

public class Collection implements Parcelable{
    @SerializedName("track")
    @Expose
    private Track mTrack;
    @SerializedName("score")
    @Expose
    private int mScore;

    protected Collection(Parcel in) {
        mScore = in.readInt();
        mTrack = in.readParcelable(Track.class.getClassLoader());
    }

    public static final Creator<Collection> CREATOR = new Creator<Collection>() {
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public Collection createFromParcel(Parcel in) {
            return new Collection(in);
        }

        @Override
        public Collection[] newArray(int size) {
            return new Collection[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mScore);
    }
}

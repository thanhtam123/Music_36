package com.example.admin.musicplayer.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TamTT on 9/14/2018.
 */

public class Genre implements Parcelable{
    @SerializedName("genre")
    @Expose
    private String mGenre;
    @SerializedName("kind")
    @Expose
    private String mKind;
    @SerializedName("last_updated")
    @Expose
    private String mLastUpdated;
    @SerializedName("collection")
    @Expose
    private ArrayList<Collection> mCollections;
    @SerializedName("query_urn")
    @Expose
    private String mQueryUrn;
    @SerializedName("next_href")
    @Expose
    private String mNextHref;

    public List getListTrackFromGenre(Genre genre) {
        List<Collection> collections = new ArrayList<>();
        collections.addAll(genre.getCollections());
        ArrayList<Track> tracks = new ArrayList<>();
        for (Collection c : collections) {
            tracks.add(c.getTrack());
        }
        return tracks;
    }

    protected Genre(Parcel in) {
        mGenre = in.readString();
        mKind = in.readString();
        mLastUpdated = in.readString();
        mQueryUrn = in.readString();
        mNextHref = in.readString();
        mCollections = in.createTypedArrayList(Collection.CREATOR);
    }

    public static final Creator<Genre> CREATOR = new Creator<Genre>() {
        @Override
        public Genre createFromParcel(Parcel in) {
            return new Genre(in);
        }

        @Override
        public Genre[] newArray(int size) {
            return new Genre[size];
        }
    };

    public String getGenre() {
        return mGenre;
    }

    public void setGenre(String genre) {
        this.mGenre = genre;
    }

    public String getKind() {
        return mKind;
    }

    public void setKind(String kind) {
        this.mKind = kind;
    }

    public String getLastUpdated() {
        return mLastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.mLastUpdated = lastUpdated;
    }

    public List<Collection> getCollections() {
        return mCollections;
    }

    public void setCollections(ArrayList<Collection> collections) {
        this.mCollections = collections;
    }

    public String getQueryUrn() {
        return mQueryUrn;
    }

    public void setQueryUrn(String queryUrn) {
        this.mQueryUrn = queryUrn;
    }

    public String getNextHref() {
        return mNextHref;
    }

    public void setNextHref(String nextHref) {
        this.mNextHref = nextHref;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "mGenre='" + mGenre + '\'' +
                ", mKind='" + mKind + '\'' +
                ", mLastUpdated='" + mLastUpdated + '\'' +
                ", mCollections=" + mCollections +
                ", mQueryUrn='" + mQueryUrn + '\'' +
                ", mNextHref='" + mNextHref + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mGenre);
        parcel.writeString(mKind);
        parcel.writeString(mLastUpdated);
        parcel.writeString(mQueryUrn);
        parcel.writeString(mNextHref);
        parcel.writeList(mCollections);
    }
}

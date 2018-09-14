package com.example.admin.musicplayer.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TamTT on 9/14/2018.
 */

public class Genre {
    @SerializedName("genre")
    @Expose
    private String mGenre;
    @SerializedName("kind")
    @Expose
    private String mKind;
    @SerializedName("last_updated")
    @Expose
    private String mLastUpdated;
    @SerializedName("collections")
    @Expose
    private List<Collection> mCollections;
    @SerializedName("query_urn")
    @Expose
    private String mQueryUrn;
    @SerializedName("next_href")
    @Expose
    private String mNextHref;

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

    public void setCollections(List<Collection> collections) {
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
}

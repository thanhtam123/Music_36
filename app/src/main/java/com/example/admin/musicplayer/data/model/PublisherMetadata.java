package com.example.admin.musicplayer.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TamTT on 9/14/2018.
 */

public class PublisherMetadata {
    @SerializedName("id")
    @Expose
    private int mId;
    @SerializedName("urn")
    @Expose
    private String mUrn;
    @SerializedName("artist")
    @Expose
    private String mArtist;
    @SerializedName("contains_music")
    @Expose
    private boolean mContainsMusic;
    @SerializedName("publisher")
    @Expose
    private String mPublisher;
    @SerializedName("isrc")
    @Expose
    private String mIsrc;
    @SerializedName("explicit")
    @Expose
    private boolean mExplicit;
    @SerializedName("writer_composer")
    @Expose
    private String mWriterComposer;
    @SerializedName("album_title")
    @Expose
    private String mAlbumTitle;
    @SerializedName("iswc")
    @Expose
    private String mIswc;
    @SerializedName("upc_or_ean")
    @Expose
    private String mUpcOrEan;
    @SerializedName("p_line")
    @Expose
    private String mPLine;
    @SerializedName("p_line_for_display")
    @Expose
    private String mPLineForDisplay;
    @SerializedName("release_title")
    @Expose
    private String mReleaseTitle;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getUrn() {
        return mUrn;
    }

    public void setUrn(String urn) {
        mUrn = urn;
    }

    public String getArtist() {
        return mArtist;
    }

    public void setArtist(String artist) {
        mArtist = artist;
    }

    public boolean isContainsMusic() {
        return mContainsMusic;
    }

    public void setContainsMusic(boolean containsMusic) {
        mContainsMusic = containsMusic;
    }

    public String getPublisher() {
        return mPublisher;
    }

    public void setPublisher(String publisher) {
        mPublisher = publisher;
    }

    public String getIsrc() {
        return mIsrc;
    }

    public void setIsrc(String isrc) {
        mIsrc = isrc;
    }

    public boolean isExplicit() {
        return mExplicit;
    }

    public void setExplicit(boolean explicit) {
        mExplicit = explicit;
    }

    public String getWriterComposer() {
        return mWriterComposer;
    }

    public void setWriterComposer(String writerComposer) {
        mWriterComposer = writerComposer;
    }

    public String getAlbumTitle() {
        return mAlbumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        mAlbumTitle = albumTitle;
    }

    public String getIswc() {
        return mIswc;
    }

    public void setIswc(String iswc) {
        mIswc = iswc;
    }

    public String getUpcOrEan() {
        return mUpcOrEan;
    }

    public void setUpcOrEan(String upcOrEan) {
        mUpcOrEan = upcOrEan;
    }

    public String getPLine() {
        return mPLine;
    }

    public void setPLine(String PLine) {
        mPLine = PLine;
    }

    public String getPLineForDisplay() {
        return mPLineForDisplay;
    }

    public void setPLineForDisplay(String PLineForDisplay) {
        mPLineForDisplay = PLineForDisplay;
    }

    public String getReleaseTitle() {
        return mReleaseTitle;
    }

    public void setReleaseTitle(String releaseTitle) {
        mReleaseTitle = releaseTitle;
    }

    @Override
    public String toString() {
        return "PublisherMetadata{" +
                "mId=" + mId +
                ", mUrn='" + mUrn + '\'' +
                ", mArtist='" + mArtist + '\'' +
                ", mContainsMusic=" + mContainsMusic +
                ", mPublisher='" + mPublisher + '\'' +
                ", mExplicit=" + mExplicit +
                ", mAlbumTitle='" + mAlbumTitle + '\'' +
                '}';
    }
}

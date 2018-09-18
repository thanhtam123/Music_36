package com.example.admin.musicplayer.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TamTT on 9/14/2018.
 */

public class Track implements Parcelable {
    @SerializedName("artwork_url")
    @Expose
    private String mArtworkUrl;
    @SerializedName("description")
    @Expose
    private String mDescription;
    @SerializedName("downloadable")
    @Expose
    private boolean mDownloadable;
    @SerializedName("download_count")
    @Expose
    private int mDownloadCount;
    @SerializedName("download_url")
    @Expose
    private String mDownloadUrl;
    @SerializedName("duration")
    @Expose
    private int mDuration;
    @SerializedName("full_duration")
    @Expose
    private int mFullDuration;
    @SerializedName("genre")
    @Expose
    private String mGenre;
    @SerializedName("has_downloads_left")
    @Expose
    private boolean mHasDownloadsLeft;
    @SerializedName("id")
    @Expose
    private int mId;
    @SerializedName("kind")
    @Expose
    private String mKind;
    @SerializedName("label_name")
    @Expose
    private String mLabelName;
    @SerializedName("last_modified")
    @Expose
    private String mLastModified;
    @SerializedName("likes_count")
    @Expose
    private int mLikesCount;
    @SerializedName("permalink")
    @Expose
    private String mPermalink;
    @SerializedName("permalink_url")
    @Expose
    private String mPermalinkUrl;
    @SerializedName("playback_count")
    @Expose
    private int mPlaybackCount;
    @SerializedName("public")
    @Expose
    private boolean mPublic;
    @SerializedName("publisher_metadata")
    @Expose
    private PublisherMetadata mPublisherMetadata;
    @SerializedName("streamable")
    @Expose
    private boolean mStreamable;
    @SerializedName("tag_list")
    @Expose
    private String mTagList;
    @SerializedName("title")
    @Expose
    private String mTitle;
    @SerializedName("uri")
    @Expose
    private String mUri;
    @SerializedName("urn")
    @Expose
    private String mUrn;
    @SerializedName("user_id")
    @Expose
    private int mUserId;
    @SerializedName("waveform_url")
    @Expose
    private String mWaveformUrl;

    protected Track(Parcel in) {
        mArtworkUrl = in.readString();
        mDescription = in.readString();
        mDownloadable = in.readByte() != 0;
        mDownloadCount = in.readInt();
        mDownloadUrl = in.readString();
        mDuration = in.readInt();
        mFullDuration = in.readInt();
        mGenre = in.readString();
        mHasDownloadsLeft = in.readByte() != 0;
        mId = in.readInt();
        mKind = in.readString();
        mLabelName = in.readString();
        mLastModified = in.readString();
        mLikesCount = in.readInt();
        mPermalink = in.readString();
        mPermalinkUrl = in.readString();
        mPlaybackCount = in.readInt();
        mPublic = in.readByte() != 0;
        mPublisherMetadata = in.readParcelable(PublisherMetadata.class.getClassLoader());
        mStreamable = in.readByte() != 0;
        mTagList = in.readString();
        mTitle = in.readString();
        mUri = in.readString();
        mUrn = in.readString();
        mUserId = in.readInt();
        mWaveformUrl = in.readString();
    }

    public static final Creator<Track> CREATOR = new Creator<Track>() {
        @Override
        public Track createFromParcel(Parcel in) {
            return new Track(in);
        }

        @Override
        public Track[] newArray(int size) {
            return new Track[size];
        }
    };

    public String getArtworkUrl() {
        return mArtworkUrl;
    }

    public void setArtworkUrl(String artworkUrl) {
        this.mArtworkUrl = artworkUrl;
    }

    public String getDescription() {
        return mDescription;
    }

    public boolean getDownloadable() {
        return mDownloadable;
    }

    public void setDownloadable(boolean downloadable) {
        this.mDownloadable = downloadable;
    }

    public int getDownloadCount() {
        return mDownloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.mDownloadCount = downloadCount;
    }

    public String getDownloadUrl() {
        return mDownloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.mDownloadUrl = downloadUrl;
    }

    public int getDuration() {
        return mDuration;
    }

    public void setDuration(int duration) {
        this.mDuration = duration;
    }

    public int getFullDuration() {
        return mFullDuration;
    }

    public void setFullDuration(int fullDuration) {
        this.mFullDuration = fullDuration;
    }


    public String getGenre() {
        return mGenre;
    }

    public boolean getHasDownloadsLeft() {
        return mHasDownloadsLeft;
    }

    public void setHasDownloadsLeft(boolean hasDownloadsLeft) {
        this.mHasDownloadsLeft = hasDownloadsLeft;
    }

    public PublisherMetadata getPublisherMetadata() {
        return mPublisherMetadata;
    }

    public void setPublisherMetadata(PublisherMetadata publisherMetadata) {
        mPublisherMetadata = publisherMetadata;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public String getKind() {
        return mKind;
    }

    public void setKind(String kind) {
        this.mKind = kind;
    }

    public String getLabelName() {
        return mLabelName;
    }

    public String getLastModified() {
        return mLastModified;
    }

    public int getLikesCount() {
        return mLikesCount;
    }

    public String getPermalink() {
        return mPermalink;
    }

    public String getPermalinkUrl() {
        return mPermalinkUrl;
    }

    public int getPlaybackCount() {
        return mPlaybackCount;
    }

    public boolean get_public() {
        return mPublic;
    }

    public boolean getStreamable() {
        return mStreamable;
    }

    public void setStreamable(boolean streamable) {
        this.mStreamable = streamable;
    }

    public String getTagList() {
        return mTagList;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getUri() {
        return mUri;
    }

    public void setUri(String uri) {
        this.mUri = uri;
    }

    public String getUrn() {
        return mUrn;
    }

    public void setUrn(String urn) {
        this.mUrn = urn;
    }

    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int userId) {
        this.mUserId = userId;
    }

    public String getWaveformUrl() {
        return mWaveformUrl;
    }

    @Override
    public String toString() {
        return "Track{" + "mArtUrl " + mArtworkUrl +
                ", mDownloadable=" + mDownloadable +
                ", mDownloadCount=" + mDownloadCount +
                ", mDownloadUrl='" + mDownloadUrl + '\'' +
                ", mDuration=" + mDuration +
                ", mFullDuration=" + mFullDuration +
                ", mGenre='" + mGenre + '\'' +
                ", mHasDownloadsLeft=" + mHasDownloadsLeft +
                ", mId=" + mId +
                ", mKind='" + mKind + '\'' +
                ", mLabelName=" + mLabelName +
                ", mTitle='" + mTitle + '\'' +
                ", mUri='" + mUri + '\'' +
                ", mWaveformUrl='" + mWaveformUrl + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mArtworkUrl);
        parcel.writeString(mDescription);
        parcel.writeByte((byte) (mDownloadable ? 1 : 0));
        parcel.writeInt(mDownloadCount);
        parcel.writeString(mDownloadUrl);
        parcel.writeInt(mDuration);
        parcel.writeInt(mFullDuration);
        parcel.writeString(mGenre);
        parcel.writeByte((byte) (mHasDownloadsLeft ? 1 : 0));
        parcel.writeInt(mId);
        parcel.writeString(mKind);
        parcel.writeString(mLabelName);
        parcel.writeString(mLastModified);
        parcel.writeInt(mLikesCount);
        parcel.writeString(mPermalink);
        parcel.writeString(mPermalinkUrl);
        parcel.writeInt(mPlaybackCount);
        parcel.writeByte((byte) (mPublic ? 1 : 0));
        parcel.writeParcelable(mPublisherMetadata, i);
        parcel.writeByte((byte) (mStreamable ? 1 : 0));
        parcel.writeString(mTagList);
        parcel.writeString(mTitle);
        parcel.writeString(mUri);
        parcel.writeString(mUrn);
        parcel.writeInt(mUserId);
        parcel.writeString(mWaveformUrl);
    }
}

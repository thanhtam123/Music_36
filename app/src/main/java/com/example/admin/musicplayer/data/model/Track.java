package com.example.admin.musicplayer.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TamTT on 9/14/2018.
 */

public class Track {
    @SerializedName("artwork_url")
    @Expose
    private String mArtworkUrl;
    @SerializedName("commentable")
    @Expose
    private boolean mCommentable;
    @SerializedName("comment_count")
    @Expose
    private int mCommentCount;
    @SerializedName("created_at")
    @Expose
    private String mCreatedAt;
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
    @SerializedName("embeddable_by")
    @Expose
    private String mEmbeddableBy;
    @SerializedName("genre")
    @Expose
    private String mGenre;
    @SerializedName("has_downloads_left")
    @Expose
    private boolean mHasDownloadsLeft;
    @SerializedName("id")
    @Expose
    public int mId;
    @SerializedName("kind")
    @Expose
    private String mKind;
    @SerializedName("label_name")
    @Expose
    private String mLabelName;
    @SerializedName("last_modified")
    @Expose
    private String mLastModified;
    @SerializedName("license")
    @Expose
    private String mLicense;
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
    @SerializedName("purchase_title")
    @Expose
    private String mPurchaseTitle;
    @SerializedName("purchase_url")
    @Expose
    private String mPurchaseUrl;
    @SerializedName("release_date")
    @Expose
    private String mReleaseDate;
    @SerializedName("reposts_count")
    @Expose
    private int mRepostsCount;
    @SerializedName("secret_token")
    @Expose
    private String mSecretToken;
    @SerializedName("sharing")
    @Expose
    private String mSharing;
    @SerializedName("state")
    @Expose
    private String mState;
    @SerializedName("streamable")
    @Expose
    private boolean mStreamable;
    @SerializedName("tag_list")
    @Expose
    private String mTagList;
    @SerializedName("title")
    @Expose
    public String mTitle;
    @SerializedName("uri")
    @Expose
    private String mUri;
    @SerializedName("urn")
    @Expose
    private String mUrn;
    @SerializedName("user_id")
    @Expose
    private int mUserId;
    @SerializedName("visuals")
    @Expose
    private String mVisuals;
    @SerializedName("waveform_url")
    @Expose
    private String mWaveformUrl;
    @SerializedName("display_date")
    @Expose
    private String mDisplayDate;
    @SerializedName("monetization_model")
    @Expose
    private String mMonetizationModel;
    @SerializedName("policy")
    @Expose
    private String policy;

    public String getArtworkUrl() {
        return mArtworkUrl;
    }

    public void setArtworkUrl(String artworkUrl) {
        this.mArtworkUrl = artworkUrl;
    }

    public boolean getCommentable() {
        return mCommentable;
    }

    public int getCommentCount() {
        return mCommentCount;
    }

    public String getCreatedAt() {
        return mCreatedAt;
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

    public String getEmbeddableBy() {
        return mEmbeddableBy;
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

    public String getLicense() {
        return mLicense;
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

    public String getPurchaseTitle() {
        return mPurchaseTitle;
    }

    public String getPurchaseUrl() {
        return mPurchaseUrl;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public int getRepostsCount() {
        return mRepostsCount;
    }

    public String getSecretToken() {
        return mSecretToken;
    }

    public String getSharing() {
        return mSharing;
    }

    public String getState() {
        return mState;
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

    public String getVisuals() {
        return mVisuals;
    }

    public String getWaveformUrl() {
        return mWaveformUrl;
    }

    public String getDisplayDate() {
        return mDisplayDate;
    }

    public String getPolicy() {
        return policy;
    }

    @Override
    public String toString() {
        return "Track{" +
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

}
